package com.example.fooddeliveryapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_history")
data class OrderHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val orderId: String,
    val date: String,
    val status: String,
    val itemsSummary: String,
    val itemCount: Int,
    val totalPrice: String
)
