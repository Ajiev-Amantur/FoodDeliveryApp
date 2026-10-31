package com.example.fooddeliveryapp.domain.repository

import com.example.fooddeliveryapp.domain.model.OrderHistoryModel
import kotlinx.coroutines.flow.Flow

interface OrderHistoryRepository {

    fun getAllFoodsHistory(): Flow<List<OrderHistoryModel>>

    suspend fun addFoodHistory(model: OrderHistoryModel)

    suspend fun deleteFoodHistory(model: OrderHistoryModel)
}
