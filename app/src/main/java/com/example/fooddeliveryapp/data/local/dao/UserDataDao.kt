package com.example.fooddeliveryapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fooddeliveryapp.data.local.entity.UserDataEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUserData(userData: UserDataEntity)

    @Query("SELECT * FROM user_data LIMIT 1")
    suspend fun getUserData(): UserDataEntity?

    @Query("SELECT * FROM user_data LIMIT 1")
    fun getUserDataFlow(): Flow<UserDataEntity?>

    @Query("DELETE FROM user_data")
    suspend fun clearUser()
}
