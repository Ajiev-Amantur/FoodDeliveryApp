package com.example.fooddeliveryapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fooddeliveryapp.data.local.entity.CardDataEntity
import kotlinx.coroutines.flow.Flow
@Dao
interface CardDataDao {
    @Query("SELECT * FROM card_data")
    fun getAllCards(): Flow<List<CardDataEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCard(cardData: CardDataEntity)
    
    
    @Delete
    suspend fun deleteCard(cardData: CardDataEntity)
}