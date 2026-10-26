package com.example.fooddeliveryapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_data")
data class UserDataEntity(
    @PrimaryKey val id: Int = 1,
    val name: String,
    val email: String,
    val photoUrl: String? = null,
    val phone: String,
    val bio: String,
)
