package com.example.fooddeliveryapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fooddeliveryapp.data.local.entity.AddressEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AddressDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAddress(address: AddressEntity)

    @Query("SELECT * FROM address_data")
    fun getAllAddresses(): Flow<List<AddressEntity>>

    @Delete
    suspend fun deleteAddress(address: AddressEntity)
}
