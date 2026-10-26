package com.example.fooddeliveryapp.domain.repository

import com.example.fooddeliveryapp.domain.model.AddressModel
import kotlinx.coroutines.flow.Flow

interface UserAddressRepository {

    fun getUserAddresses(): Flow<List<AddressModel>>

    suspend fun addAddressUser(userAddress: AddressModel)

    suspend fun deleteAddress(userAddress: AddressModel)
}
