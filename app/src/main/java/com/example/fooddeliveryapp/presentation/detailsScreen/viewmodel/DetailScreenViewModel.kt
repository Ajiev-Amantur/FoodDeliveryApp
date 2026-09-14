package com.example.fooddeliveryapp.presentation.detailsScreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.domain.model.FoodDataModel
import com.example.fooddeliveryapp.domain.repository.CartFoodRepository
import kotlinx.coroutines.launch

class DetailScreenViewModel(private val cartFoodRepository: CartFoodRepository): ViewModel() {

    fun addCartFood(foodItem: FoodDataModel){
        viewModelScope.launch {
            cartFoodRepository.addFoodCart(foodItem)
        }
    }
}