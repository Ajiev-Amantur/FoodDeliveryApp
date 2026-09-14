package com.example.fooddeliveryapp.presentation.cartScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.data.local.entity.CartEntity
import com.example.fooddeliveryapp.domain.model.FoodDataModel
import com.example.fooddeliveryapp.domain.repository.CartFoodRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CartScreenViewModel(private val cartFoodRepository: CartFoodRepository): ViewModel() {

    private var _cartFood = MutableStateFlow<List<CartEntity>>(emptyList())
    val cartFood : MutableStateFlow<List<CartEntity>> = _cartFood
    fun addFoodCart(foodItem: FoodDataModel){
        viewModelScope.launch {
            cartFoodRepository.addFoodCart(foodItem)
        }
    }
    init {
        loadCartFoods()
    }
    fun minusCount(foodItem: CartEntity) {
        viewModelScope.launch {
            cartFoodRepository.decreaseFoodCart(foodItem)
        }
    }
    fun deleteFood(foodItem: CartEntity){
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