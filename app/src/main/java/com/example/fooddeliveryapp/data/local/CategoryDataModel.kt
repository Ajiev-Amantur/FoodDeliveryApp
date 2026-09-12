package com.example.fooddeliveryapp.data.local

data class CategoryDataModel(
    val id: Int,
    val name: String,
    val icon: Int?, // null for "All",
)