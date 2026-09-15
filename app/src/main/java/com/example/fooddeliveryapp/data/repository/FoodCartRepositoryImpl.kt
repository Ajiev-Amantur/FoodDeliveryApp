package com.example.fooddeliveryapp.data.repository

import com.example.fooddeliveryapp.data.local.dao.CartFoodDao
import com.example.fooddeliveryapp.data.local.entity.CartFoodEntity
import com.example.fooddeliveryapp.data.mapper.toEntity
import com.example.fooddeliveryapp.data.mapper.toModel
import com.example.fooddeliveryapp.domain.model.CartFoodModel
import com.example.fooddeliveryapp.domain.model.FoodDataModel
import com.example.fooddeliveryapp.domain.repository.CartFoodRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FoodCartRepositoryImpl(val cartFoodDao: CartFoodDao): CartFoodRepository {
    override suspend fun addFoodCart(foodItem: FoodDataModel) {
        val savedCartFood = cartFoodDao.getItemByName(foodItem.name)
        if (savedCartFood != null){
            val updatedCartFood = savedCartFood.copy(quantity = savedCartFood.quantity + 1)
            cartFoodDao.addFood(updatedCartFood)
        }else{
            val itemFood = CartFoodEntity(
                name = foodItem.name,
                price = foodItem.price,
                image = foodItem.image,
                quantity = 1
            )
            cartFoodDao.addFood(itemFood)
        }
    }

    override suspend fun decreaseFoodCart(cartItem: CartFoodModel) {
        val cartFoodModel = cartItem.toEntity()
        if (cartFoodModel.quantity > 1) {
            cartFoodDao.addFood(cartFoodModel.copy(quantity = cartFoodModel.quantity - 1))
        } else {
            cartFoodDao.deleteFood(cartFoodModel)
        }
    }

    override suspend fun deleteFoodCart(cartItem: CartFoodModel) {
        val cartFoodModel = cartItem.toEntity()

        cartFoodDao.deleteFood(cartFoodModel)
    }

    override fun getCartFoods(): Flow<List<CartFoodModel>> {
        return cartFoodDao.getAllCartFood().map { list ->
            list.map { it.toModel() }
        }
    }
}
