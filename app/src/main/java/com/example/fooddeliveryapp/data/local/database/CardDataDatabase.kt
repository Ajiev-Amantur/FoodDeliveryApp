package com.example.fooddeliveryapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fooddeliveryapp.data.local.dao.CardDataDao
import com.example.fooddeliveryapp.data.local.entity.CardDataEntity

@Database(entities = [CardDataEntity::class], version = 1)
abstract class CardDataDatabase(): RoomDatabase() {

    abstract fun cartDataDao(): CardDataDao
}