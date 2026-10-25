package com.example.fooddeliveryapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fooddeliveryapp.data.local.dao.AddressDao
import com.example.fooddeliveryapp.data.local.entity.AddressEntity

@Database(entities = [AddressEntity::class], version = 1)
abstract class UserAdressesDataBase : RoomDatabase() {
    abstract fun dao(): AddressDao
}
