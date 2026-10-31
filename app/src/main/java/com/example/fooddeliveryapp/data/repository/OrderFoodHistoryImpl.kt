package com.example.fooddeliveryapp.data.repository

import com.example.fooddeliveryapp.data.local.dao.OrderHistoryDao
import com.example.fooddeliveryapp.data.mapper.toEntity
import com.example.fooddeliveryapp.data.mapper.toModel
import com.example.fooddeliveryapp.domain.model.OrderHistoryModel
import com.example.fooddeliveryapp.domain.repository.OrderHistoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OrderFoodHistoryImpl(private val dao: OrderHistoryDao): OrderHistoryRepository {
    override fun getAllFoodsHistory(): Flow<List<OrderHistoryModel>> {
        return dao.getAllFoodHistory().map { entities ->
            entities.map { it.toModel() }
        }
    }

    override suspend fun addFoodHistory(model: OrderHistoryModel) {
        dao.addFoodHistory(model.toEntity())
    }

    override suspend fun deleteFoodHistory(model: OrderHistoryModel) {
        dao.deleteOrderHistory(model.toEntity())
    }
}