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
import java.util.Locale

class FoodCartRepositoryImpl(val cartFoodDao: CartFoodDao): CartFoodRepository {
    override suspend fun addFoodCart(foodItem: FoodDataModel) {
        val savedCartFood = cartFoodDao.getItemByName(foodItem.name)
        if (savedCartFood != null){
            val currentPrice = savedCartFood.price.toDoubleOrNull() ?: 0.0
            val unitPrice = currentPrice  /savedCartFood.quantity
            val newPrice = String.format(java.util.Locale.US, "%.2f", currentPrice + unitPrice)

            val updatedCartFood = savedCartFood.copy(
                quantity = savedCartFood.quantity + 1,
                price = newPrice
            )
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
            val price = cartItem.price.toDoubleOrNull() ?: 0.0
            val unitPrice = price / cartItem.quantity // Вычисляем цену за 1 штуку
            val newPrice = String.format(Locale.US, "%.2f", price - unitPrice)
            cartFoodDao.addFood(cartFoodModel.copy(
                quantity = cartFoodModel.quantity - 1,
                price = newPrice
            )
            )
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
