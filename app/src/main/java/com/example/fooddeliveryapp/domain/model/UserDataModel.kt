package com.example.fooddeliveryapp.domain.model

data class UserDataModel(
    val name: String,
    val email: String,
    val photoUrl: String? = null,
    val phone: String,
    val bio: String,
)
