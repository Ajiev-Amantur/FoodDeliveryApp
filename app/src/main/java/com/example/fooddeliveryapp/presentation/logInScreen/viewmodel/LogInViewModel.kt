package com.example.fooddeliveryapp.presentation.logInScreen.viewmodel

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LogInViewModel(private val authRepository: AuthRepository): ViewModel() {

    fun signInViewModel(
        email: String,
        password: String,
        onResult: (Boolean) -> Unit
    ){
        viewModelScope.launch {
            val isSuccess = authRepository.signInWithEmail(
                email,
                password
            )
            onResult(isSuccess)
        }
    }

    fun generateCode(): String{
        return (1000..9999).random().toString()
    }
    fun resetPassword(email: String, onResult: (Boolean) -> Unit){
        viewModelScope.launch {
            val isSucsess = authRepository.resetPassword(email)
            onResult(isSucsess)
        }
    }
}