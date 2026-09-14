package com.example.fooddeliveryapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fooddeliveryapp.data.local.dao.CartFoodDao
import com.example.fooddeliveryapp.data.local.entity.CartEntity

@Database(entities = [CartEntity::class], version = 1)
abstract class CartFoodDataBase: RoomDatabase() {
    abstract fun cartDao(): CartFoodDao
}