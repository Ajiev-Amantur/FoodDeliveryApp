package com.example.fooddeliveryapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "address_data")
data class AddressEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val address: String,
    val street: String,
    val postCode: String,
    val appartment: String,
    val label: String
)
