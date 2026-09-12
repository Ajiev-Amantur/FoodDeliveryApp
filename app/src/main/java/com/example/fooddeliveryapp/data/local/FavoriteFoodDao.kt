package com.example.fooddeliveryapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fooddeliveryapp.data.local.model.FavoriteFoodEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteFoodDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun addFood(list: FavoriteFoodEntity)

    @Delete
    suspend fun removeFavorite(food: FavoriteFoodEntity)

    @Query("SELECT * FROM favoritefoodentity")
     fun getAllFavorites(): Flow<List<FavoriteFoodEntity>>
}