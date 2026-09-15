package com.example.fooddeliveryapp.domain.repository

import com.example.fooddeliveryapp.data.local.entity.CartFoodEntity
import com.example.fooddeliveryapp.domain.model.CartFoodModel
import com.example.fooddeliveryapp.domain.model.FoodDataModel
import kotlinx.coroutines.flow.Flow

interface CartFoodRepository {
    suspend fun addFoodCart(foodItem: FoodDataModel)
    suspend fun decreaseFoodCart(cartItem: CartFoodModel)
    suspend fun  deleteFoodCart(cartItem: CartFoodModel)
    fun getCartFoods(): Flow<List<CartFoodModel>>
}
