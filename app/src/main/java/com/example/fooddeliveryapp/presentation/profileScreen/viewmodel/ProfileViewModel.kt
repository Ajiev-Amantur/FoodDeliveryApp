package com.example.fooddeliveryapp.presentation.profileScreen.viewmodel

import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.domain.model.UserDataModel
import com.example.fooddeliveryapp.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val authRepository: AuthRepository
): ViewModel() {

    val selectedUri = mutableStateListOf<Uri>()
    init {
        getUserData()
    }

    fun updateUri(newUri: List<Uri>){
        selectedUri.clear()
        selectedUri.addAll(newUri)
    }
    fun clearPhotoPicker(){
        selectedUri.clear()
    }
    private var _userData = MutableStateFlow<UserDataModel?>(null)
    val userData: MutableStateFlow<UserDataModel?> = _userData
    fun getUserData(){
        viewModelScope.launch {
            try {
                val userData = authRepository.getUserData()
                _userData.value = userData
            } catch (e: Exception) {
                println("ProfileViewModel Error: ${e.message}")
                e.printStackTrace()
            }
        }
    }
}