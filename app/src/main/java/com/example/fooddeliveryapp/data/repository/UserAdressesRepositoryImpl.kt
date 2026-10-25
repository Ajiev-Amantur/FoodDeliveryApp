package com.example.fooddeliveryapp.data.repository

import com.example.fooddeliveryapp.data.local.dao.AddressDao
import com.example.fooddeliveryapp.data.mapper.toEntity
import com.example.fooddeliveryapp.data.mapper.toModel
import com.example.fooddeliveryapp.domain.model.AddressModel
import com.example.fooddeliveryapp.domain.repository.UserAdressRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserAdressesRepositoryImpl(private val dao: AddressDao) : UserAdressRepository {

    override fun getUserAdresses(): Flow<List<AddressModel>> {
        return dao.getAllAddresses().map { entities ->
            entities.map { it.toModel() }
        }
    }

    override suspend fun addAdressUser(userAdress: AddressModel) {
        dao.insertAddress(userAdress.toEntity())
    }

    override suspend fun deleteAdress(userAdress: AddressModel) {
        dao.deleteAddress(userAdress.toEntity())
    }
}
