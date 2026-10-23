package com.example.fooddeliveryapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fooddeliveryapp.data.local.dao.UserDataDao
import com.example.fooddeliveryapp.data.local.entity.UserDataEntity

@Database(entities = [UserDataEntity::class], version = 2)
abstract class UserDatabase: RoomDatabase() {

    abstract fun dao(): UserDataDao
}