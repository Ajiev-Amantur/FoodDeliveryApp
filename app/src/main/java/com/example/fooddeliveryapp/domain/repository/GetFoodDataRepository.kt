package com.example.fooddeliveryapp.domain.repository

import com.example.fooddeliveryapp.data.CategoryDataModel
import com.example.fooddeliveryapp.domain.model.FoodDataModel

interface GetFoodDataRepository {
    suspend fun getFoodData(): List<FoodDataModel>
    suspend fun getCategories(): List<CategoryDataModel>
    suspend fun toggleFavorite(food: FoodDataModel)
}
