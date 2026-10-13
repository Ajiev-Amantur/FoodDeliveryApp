package com.example.fooddeliveryapp.presentation.profileScreen.viewmodel

import androidx.lifecycle.ViewModel
import com.example.fooddeliveryapp.domain.model.UserDataModel
import com.example.fooddeliveryapp.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow

class ProfileViewModel(
    private val authRepository: AuthRepository
): ViewModel() {

    private var _userData = MutableStateFlow<UserDataModel?>(null)
    val userData: MutableStateFlow<UserDataModel?> = _userData
    fun getUserData(){
       val userData = authRepository.getUserData()

        _userData.value = userData
    }
}