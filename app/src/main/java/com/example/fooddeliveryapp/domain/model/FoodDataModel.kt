package com.example.fooddeliveryapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class FoodDataModel(
    val name: String,
    val image: Int,
    val price: String,
    val description: String,
    var isFavorite: Boolean = false
)