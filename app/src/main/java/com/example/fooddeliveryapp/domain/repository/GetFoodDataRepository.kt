package com.example.fooddeliveryapp.domain.repository

import com.example.fooddeliveryapp.data.CategoryDataModel
import com.example.fooddeliveryapp.data.FoodDataModel

interface GetFoodDataRepository {
    suspend fun getFoodData(): List<FoodDataModel>
    suspend fun getCategories(): List<CategoryDataModel>
}
