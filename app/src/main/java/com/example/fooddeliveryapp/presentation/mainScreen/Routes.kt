package com.example.fooddeliveryapp.presentation.mainScreen

import com.example.fooddeliveryapp.domain.model.FoodDataModel
import kotlinx.serialization.Serializable

@Serializable
object OnboardingRoute

@Serializable
object HomeRoute

@Serializable
object SavedFoodRoute

@Serializable
data class DetailRoute(
    val foodItem: FoodDataModel
)
