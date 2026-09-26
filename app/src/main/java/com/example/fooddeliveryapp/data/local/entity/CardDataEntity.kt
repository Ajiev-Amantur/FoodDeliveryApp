package com.example.fooddeliveryapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card_data")
data class CardDataEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val cardName: String,
    val holderName: String,
    val cardNumber: String,
    val dateCard: String,
    val CVC: String,
)