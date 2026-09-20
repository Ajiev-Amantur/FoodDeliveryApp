package com.example.fooddeliveryapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fooddeliveryapp.data.local.entity.FavoriteFoodEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteFoodDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun addFood(list: FavoriteFoodEntity)

    @Delete
    suspend fun removeFavorite(food: FavoriteFoodEntity)

    @Query("DELETE FROM favorites WHERE name = :name")
    suspend fun deleteFavoriteByName(name: String)

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE name = :name)")
    suspend fun isFavorite(name: String): Boolean

    @Query("SELECT * FROM favorites")
     fun getAllFavorites(): Flow<List<FavoriteFoodEntity>>
}