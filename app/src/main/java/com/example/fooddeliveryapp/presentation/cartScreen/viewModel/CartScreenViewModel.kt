package com.example.fooddeliveryapp.presentation.cartScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.data.local.entity.CartFoodEntity
import com.example.fooddeliveryapp.domain.model.CartFoodModel
import com.example.fooddeliveryapp.domain.model.FoodDataModel
import com.example.fooddeliveryapp.domain.repository.CartFoodRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CartScreenViewModel(private val cartFoodRepository: CartFoodRepository): ViewModel() {

    private var _cartFood = MutableStateFlow<List<CartFoodModel>>(emptyList())
    val cartFood : MutableStateFlow<List<CartFoodModel>> = _cartFood
    fun addFoodCart(foodItem: FoodDataModel){
        viewModelScope.launch {
            cartFoodRepository.addFoodCart(foodItem)
        }
    }
    init {
        loadCartFoods()
    }
    fun minusCount(foodItem: CartFoodModel) {
        viewModelScope.launch {
            cartFoodRepository.decreaseFoodCart(foodItem)
        }
    }
    fun deleteFood(foodItem: CartFoodModel){
        viewModelScope.launch {
            cartFoodRepository.deleteFoodCart(foodItem)
        }
    }
    fun loadCartFoods(){
        viewModelScope.launch {
            cartFoodRepository.getCartFoods().collect { updatedList ->
                _cartFood.value = updatedList

            }
        }
    }

}