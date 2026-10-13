package com.example.fooddeliveryapp.domain.model

data class UserDataModel(
    val name: String,
    val email: String,
    val photoUrl: String? = null
)
