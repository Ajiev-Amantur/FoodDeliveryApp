package com.example.fooddeliveryapp.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteFoodEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val image: Int,
    val price: String,
    val description: String,
    var isFavorite: Boolean = false
)