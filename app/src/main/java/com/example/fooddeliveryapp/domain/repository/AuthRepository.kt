package com.example.fooddeliveryapp.domain.repository

import com.example.fooddeliveryapp.domain.model.UserDataModel

interface AuthRepository {
    suspend fun signInWithGoogle(): Boolean
    suspend fun signInWithEmail(email: String, password: String): Boolean
    suspend fun signUpWithEmail(email: String, password: String): Boolean
    suspend fun saveUserData(userDataModel: UserDataModel)
    suspend fun resetPassword(email: String): Boolean
    suspend fun getUserData(): UserDataModel?

}