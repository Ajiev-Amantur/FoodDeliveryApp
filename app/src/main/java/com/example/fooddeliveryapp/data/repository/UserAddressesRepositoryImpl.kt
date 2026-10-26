package com.example.fooddeliveryapp.data.repository

import com.example.fooddeliveryapp.data.local.dao.AddressDao
import com.example.fooddeliveryapp.data.mapper.toEntity
import com.example.fooddeliveryapp.data.mapper.toModel
import com.example.fooddeliveryapp.domain.model.AddressModel
import com.example.fooddeliveryapp.domain.repository.UserAddressRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserAddressesRepositoryImpl(private val dao: AddressDao) : UserAddressRepository {

    override fun getUserAddresses(): Flow<List<AddressModel>> {
        return dao.getAllAddresses().map { entities ->
            entities.map { it.toModel() }
        }
    }

    override suspend fun addAddressUser(userAddress: AddressModel) {
        dao.insertAddress(userAddress.toEntity())
    }

    override suspend fun deleteAddress(userAddress: AddressModel) {
        dao.deleteAddress(userAddress.toEntity())
    }
}
