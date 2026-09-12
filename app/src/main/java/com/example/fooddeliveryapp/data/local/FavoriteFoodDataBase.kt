package com.example.fooddeliveryapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fooddeliveryapp.data.local.model.FavoriteFoodEntity

@Database(entities = [FavoriteFoodEntity::class], version = 1)
abstract class FavoriteFoodDataBase: RoomDatabase() {

    abstract fun favoriteDao(): FavoriteFoodDao

}