package com.example.fooddeliveryapp.domain.model

data class CategoryDataModel(
    val id: Int,
    val name: String,
    val icon: Int?, // null for "All",
)