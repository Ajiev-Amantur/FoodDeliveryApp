package com.example.fooddeliveryapp.data

data class FoodDataModel(
    val name: String,
    val image: Int,
    val price: String,
    val description: String,
    var isFavorite: Boolean = false
)
