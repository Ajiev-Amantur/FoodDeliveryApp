package com.example.fooddeliveryapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fooddeliveryapp.data.local.entity.CartEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CartFoodDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFood(model: CartEntity)

    @Delete
    suspend fun deleteFood(model: CartEntity)

    @Query("SELECT * FROM cart_table")
     fun getAllCartFood(): Flow<List<CartEntity>>

    @Query("SELECT * FROM cart_table WHERE name = :name")
    suspend fun getItemByName(name: String): CartEntity?

    @Query("DELETE FROM cart_table")
    suspend fun clearCart()
}