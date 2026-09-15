package com.example.fooddeliveryapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fooddeliveryapp.data.local.entity.CartFoodEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CartFoodDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFood(model: CartFoodEntity)

    @Delete
    suspend fun deleteFood(model: CartFoodEntity)

    @Query("SELECT * FROM cart_table")
     fun getAllCartFood(): Flow<List<CartFoodEntity>>

    @Query("SELECT * FROM cart_table WHERE name = :name")
    suspend fun getItemByName(name: String): CartFoodEntity?

    @Query("DELETE FROM cart_table")
    suspend fun clearCart()
}