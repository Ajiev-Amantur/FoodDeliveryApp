package com.example.fooddeliveryapp.presentation.profileScreen.viewmodel

import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.domain.model.AddressModel
import com.example.fooddeliveryapp.domain.model.UserDataModel
import com.example.fooddeliveryapp.domain.repository.AuthRepository
import com.example.fooddeliveryapp.domain.repository.UserAdressRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val authRepository: AuthRepository,
    private val userAdressesRepository: UserAdressRepository
): ViewModel() {

    val selectedUri = mutableStateListOf<Uri>()

    fun updateUri(newUri: List<Uri>){
        selectedUri.clear()
        selectedUri.addAll(newUri)
    }

    fun clearPhotoPicker(){
        selectedUri.clear()
    }

    fun addUserAddress(addressModel: AddressModel){
        viewModelScope.launch {
            userAdressesRepository.addAdressUser(addressModel)
        }
    }

    fun deleteUserAddress(addressModel: AddressModel){
        viewModelScope.launch {
            userAdressesRepository.deleteAdress(addressModel)
        }
    }

    val userAddresses: StateFlow<List<AddressModel>> = userAdressesRepository.getUserAdresses()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val userData: StateFlow<UserDataModel?> = authRepository.getUserData()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    fun saveUserData(name: String, email: String, phone: String, bio: String) {
        viewModelScope.launch {
            try {
                val currentPhoto = selectedUri.firstOrNull()?.toString() ?: userData.value?.photoUrl
                val newUserData = UserDataModel(
                    name = name,
                    email = email,
                    phone = phone,
                    bio = bio,
                    photoUrl = currentPhoto
                )
                authRepository.saveUserData(newUserData)
            } catch (e: Exception) {
                println("ProfileViewModel Save Error: ${e.message}")
                e.printStackTrace()
            }
        }
    }
}
