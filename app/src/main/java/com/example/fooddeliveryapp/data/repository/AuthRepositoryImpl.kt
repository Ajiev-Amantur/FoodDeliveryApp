package com.example.fooddeliveryapp.data.repository

import android.content.Context
import android.widget.Toast
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.example.fooddeliveryapp.domain.repository.AuthRepository
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.cancellation.CancellationException

class AuthRepositoryImpl(private val context: Context): AuthRepository {
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
    override suspend fun signInWithGoogle(): Boolean {
        return try {
            val credintial = credentialManager
                .getCredential(context, getCredentialRequets).credential

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
            e.printStackTrace()
            false
        }
    }

    override suspend fun signInWithEmail(email: String, password: String): Boolean {
        return try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            true
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            e.printStackTrace()
            false
        }
    }

    override suspend fun signUpWithEmail(email: String, password: String): Boolean {
        return try {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            true
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            e.printStackTrace()
            false
        }
    }

}