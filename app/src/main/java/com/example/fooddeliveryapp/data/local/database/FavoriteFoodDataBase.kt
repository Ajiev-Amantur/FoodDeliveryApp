package com.example.fooddeliveryapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fooddeliveryapp.data.local.dao.FavoriteFoodDao
import com.example.fooddeliveryapp.data.local.entity.FavoriteFoodEntity

@Database(entities = [FavoriteFoodEntity::class], version = 1)
abstract class FavoriteFoodDataBase: RoomDatabase() {

    abstract fun favoriteDao(): FavoriteFoodDao

}