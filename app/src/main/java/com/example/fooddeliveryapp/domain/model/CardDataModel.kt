package com.example.fooddeliveryapp.domain.model

data class CardDataModel(
    val id: Int = 0,
    val cardName: String,
    val holderName: String,
    val cardNumber: String,
    val dateCard: String,
    val CVC: String,
)
