package com.example.fooddeliveryapp.presentation.mainScreen

import kotlinx.serialization.Serializable

@Serializable
object OnboardingRoute
@Serializable
object WelcomeRoute

@Serializable
object LoginRoute
@Serializable
object SignUpRoute
@Serializable
object ForgotPasswordRoute
@Serializable
data class VerificationRoute(val code: String,val email: String)

@Serializable
object HomeRoute

@Serializable
object SavedFoodRoute

@Serializable
object CartRoute

@Serializable
object ProfileRoute
@Serializable
object EdipProfileRoute

@Serializable
object PersonalInfoRote

@Serializable
object AddressListRoute

@Serializable
data class AddAddressRoute(
    val id: Int = 0,
    val address: String = "",
    val street: String = "",
    val postCode: String = "",
    val appartment: String = "",
    val label: String = "Home"
)

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
