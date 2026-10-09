package com.example.fooddeliveryapp.presentation.signUpScreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.domain.repository.AuthRepository
import kotlinx.coroutines.launch

class SignUpViewModel(private val authRepository: AuthRepository): ViewModel() {

    fun signUpWithEmail(
        email: String,
        password: String,
        onResult: (Boolean) -> Unit
    ){
        viewModelScope.launch {
           val isSuccess = authRepository.signUpWithEmail(
                email,
                password
            )
            onResult(isSuccess)
        }
    }
}