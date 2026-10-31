package com.example.fooddeliveryapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fooddeliveryapp.data.local.dao.OrderHistoryDao
import com.example.fooddeliveryapp.data.local.entity.OrderHistoryEntity

@Database(entities = [OrderHistoryEntity::class], version = 1)
abstract class OrderHistoryDatabase : RoomDatabase() {
    abstract fun dao(): OrderHistoryDao
}
