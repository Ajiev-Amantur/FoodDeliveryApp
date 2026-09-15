package com.example.fooddeliveryapp.domain.model

data class CartFoodModel(
    val id: Int = 0,
    val name: String,
    val price: String,
    val image: Int,
    val quantity: Int = 1 // По умолчанию 1 штука
)