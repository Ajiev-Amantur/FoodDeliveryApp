package com.example.fooddeliveryapp.domain.repository

interface AuthRepository {
    suspend fun signInWithGoogle(): Boolean
    suspend fun signInWithEmail(email: String, password: String): Boolean
    suspend fun signUpWithEmail(email: String, password: String): Boolean
}