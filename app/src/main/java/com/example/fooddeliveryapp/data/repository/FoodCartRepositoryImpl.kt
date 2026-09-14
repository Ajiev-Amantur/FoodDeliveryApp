package com.example.fooddeliveryapp.data.repository

import com.example.fooddeliveryapp.data.local.dao.CartFoodDao
import com.example.fooddeliveryapp.data.local.entity.CartEntity
import com.example.fooddeliveryapp.domain.model.FoodDataModel
import com.example.fooddeliveryapp.domain.repository.CartFoodRepository
import kotlinx.coroutines.flow.Flow

class FoodCartRepositoryImpl(val cartFoodDao: CartFoodDao): CartFoodRepository {
    override suspend fun addFoodCart(foodItem: FoodDataModel) {
        val savedCartFood = cartFoodDao.getItemByName(foodItem.name)
        if (savedCartFood != null){
            val updatedCartFood = savedCartFood.copy(quantity = savedCartFood.quantity + 1)
            cartFoodDao.addFood(updatedCartFood)
        }else{
            val itemFood = CartEntity(
                name = foodItem.name,
                price = foodItem.price,
                image = foodItem.image,
                quantity = 1
            )
            cartFoodDao.addFood(itemFood)
        }
    }

    override suspend fun decreaseFoodCart(cartItem: CartEntity) {
        if (cartItem.quantity > 1) {
            cartFoodDao.addFood(cartItem.copy(quantity = cartItem.quantity - 1))
        } else {
            cartFoodDao.deleteFood(cartItem)
        }
    }

    override suspend fun deleteFoodCart(cartItem: CartEntity) {
        cartFoodDao.deleteFood(cartItem)
    }

    override fun getCartFoods(): Flow<List<CartEntity>> {
        return cartFoodDao.getAllCartFood()
    }
}
