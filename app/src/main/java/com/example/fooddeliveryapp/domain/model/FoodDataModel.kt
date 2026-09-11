package com.example.fooddeliveryapp.domain.model

data class FoodDataModel(
    val name: String,
    val image: Int,
    val price: String,
    val description: String,
    var isFavorite: Boolean = false
)