package com.example.fooddeliveryapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_data")
data class UserDataEntity(
    @PrimaryKey val email: String,
    val name: String,
    val photoUrl: String? = null,
    val phone: String,
    val bio: String,
)