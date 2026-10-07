package com.example.fooddeliveryapp.presentation.logInScreen

import android.content.Context
import android.widget.Toast
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.cancellation.CancellationException

class AuthManager(private val context: Context) {

    private val firebaseAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val credentialManager: CredentialManager by lazy { CredentialManager.create(context) }
    private val getGoogleIdOption: GetGoogleIdOption by lazy {
        GetGoogleIdOption.Builder()
            .setServerClientId("362653133196-h5ophf7ktksnq91morc957hfjlc1ujko.apps.googleusercontent.com")
            .setFilterByAuthorizedAccounts(false)
            .setAutoSelectEnabled(false)
            .build()
    }
    private val getCredentialRequets: GetCredentialRequest by lazy {
        GetCredentialRequest.Builder()
            .addCredentialOption(getGoogleIdOption)
            .build()
    }

   suspend fun signInWithGoogle(): Boolean {
      return try {
           Toast.makeText(context, "Opening Google...", Toast.LENGTH_SHORT).show()
           val credintial = credentialManager
               .getCredential(context, getCredentialRequets).credential
           Toast.makeText(context, "Got Google Account!", Toast.LENGTH_SHORT).show()

           if (credintial is CustomCredential && credintial.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL ){
               val googleIdCredintial = GoogleIdTokenCredential.createFrom(credintial.data)
               val idToken = googleIdCredintial.idToken
               val firebaseCredintion = GoogleAuthProvider.getCredential(idToken,null)
               firebaseAuth.signInWithCredential(firebaseCredintion).await()
               true
           } else {
               false
           }
       } catch (e: Exception) {
          if (e is CancellationException) throw e
          println("Google Sign-In Error: ${e.message}")
           e.printStackTrace()
           Toast.makeText(context, "Google Error: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
           false
       }
   }
    fun signInWithEmail(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ){
        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { error ->
                onError(error.localizedMessage?: " Error in Auth")
            }
    }

    fun signUpWithEmail(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ){
        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { error->
                onError(error.localizedMessage?: " Error in Auth")
            }
    }
}