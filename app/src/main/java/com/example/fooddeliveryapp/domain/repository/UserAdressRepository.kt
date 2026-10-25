package com.example.fooddeliveryapp.domain.repository

import com.example.fooddeliveryapp.domain.model.AddressModel
import kotlinx.coroutines.flow.Flow

interface UserAdressRepository {

    fun getUserAdresses(): Flow<List<AddressModel>>

    suspend fun addAdressUser(userAdress: AddressModel)

    suspend fun deleteAdress(userAdress: AddressModel)
}