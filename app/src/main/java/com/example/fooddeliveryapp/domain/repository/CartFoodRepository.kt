package com.example.fooddeliveryapp.domain.repository

import com.example.fooddeliveryapp.data.local.entity.CartEntity
import com.example.fooddeliveryapp.domain.model.FoodDataModel
import kotlinx.coroutines.flow.Flow

interface CartFoodRepository {
    suspend fun addFoodCart(foodItem: FoodDataModel)
    suspend fun decreaseFoodCart(cartItem: CartEntity)
    suspend fun deleteFoodCart(cartItem: CartEntity)
    suspend fun clearCart()
    fun getCartFoods(): Flow<List<CartEntity>>
}
