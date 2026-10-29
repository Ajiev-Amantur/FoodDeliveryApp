package com.example.fooddeliveryapp.presentation.profileScreen.viewmodel

import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.domain.model.AddressModel
import com.example.fooddeliveryapp.domain.model.UserDataModel
import com.example.fooddeliveryapp.domain.repository.AuthRepository
import com.example.fooddeliveryapp.domain.repository.UserAddressRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val authRepository: AuthRepository,
    private val userAddressesRepository: UserAddressRepository
): ViewModel() {

    private val _personalData = MutableStateFlow<UserDataModel?>(null)
    val personalData: StateFlow<UserDataModel?> = _personalData.asStateFlow()
    val selectedUri = mutableStateListOf<Uri>()

    init {
        getUserData()
    }

    fun getUserData() {
        viewModelScope.launch {
            try {
                authRepository.getUserData().collect { data ->
                    _personalData.value = data
                }
            } catch (e: Exception) {
                println("ProfileViewModel Error: ${e.message}")
                e.printStackTrace()
            }
        }
    }

    fun updateUri(newUri: List<Uri>){
        selectedUri.clear()
        selectedUri.addAll(newUri)
    }

    fun clearPhotoPicker(){
        selectedUri.clear()
    }

    fun addUserAddress(addressModel: AddressModel){
        viewModelScope.launch {
            userAddressesRepository.addAddressUser(addressModel)
        }
    }

    fun deleteUserAddress(addressModel: AddressModel){
        viewModelScope.launch {
            userAddressesRepository.deleteAddress(addressModel)
        }
    }

    val userAddresses: StateFlow<List<AddressModel>> = userAddressesRepository.getUserAddresses()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun saveUserData(name: String, email: String, phone: String, bio: String) {
        viewModelScope.launch {
            try {
                val currentPhoto = selectedUri.firstOrNull()?.toString() ?: _personalData.value?.photoUrl ?: ""
                val newUserData = UserDataModel(
                    name = name,
                    email = email,
                    phone = phone,
                    bio = bio,
                    photoUrl = currentPhoto
                )
                authRepository.saveUserData(newUserData)
                _personalData.value = newUserData
            } catch (e: Exception) {
                println("ProfileViewModel Save Error: ${e.message}")
                e.printStackTrace()
            }
        }
    }
}
