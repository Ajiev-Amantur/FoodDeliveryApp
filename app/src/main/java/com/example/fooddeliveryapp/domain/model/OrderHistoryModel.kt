package com.example.fooddeliveryapp.domain.model

data class OrderHistoryModel(
    val orderId: String,
    val date: String,
    val status: String,
    val itemsSummary: String,
    val itemCount: Int,
    val totalPrice: String
)
