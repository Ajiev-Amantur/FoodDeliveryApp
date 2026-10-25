package com.example.fooddeliveryapp.domain.model

data class AddressModel(
    val id: Int = 0,
    val address: String,
    val street: String,
    val postCode: String,
    val appartment: String,
    val label: String
)
