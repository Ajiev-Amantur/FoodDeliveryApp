package com.example.fooddeliveryapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fooddeliveryapp.data.local.entity.OrderHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderHistoryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFoodHistory(entity: OrderHistoryEntity)

    @Delete
    suspend fun deleteOrderHistory(entity: OrderHistoryEntity)

    @Query("SELECT * FROM food_history")
    fun getAllFoodHistory(): Flow<List<OrderHistoryEntity>>
}
