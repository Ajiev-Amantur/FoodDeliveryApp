package com.example.fooddeliveryapp.presentation.mainScreen

import kotlinx.serialization.Serializable

@Serializable
object OnboardingRoute
@Serializable
object WelcomeRoute

@Serializable
object HomeRoute

@Serializable
object SavedFoodRoute

@Serializable
object CartRoute

@Serializable
object PaymentRoute
@Serializable
data class AddCardRoute(val cardName: String)
@Serializable
object SuccessRoute
@Serializable
data class DetailRoute(
    val name: String,
    val image: Int,
    val price: String,
    val description: String,
    val isFavorite: Boolean
)

@Serializable
object SucsessCardAdded
