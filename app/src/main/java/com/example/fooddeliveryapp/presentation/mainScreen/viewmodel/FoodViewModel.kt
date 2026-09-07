package com.example.fooddeliveryapp.presentation.mainScreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.data.FoodDataModel
import com.example.fooddeliveryapp.domain.repository.GetFoodDataRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FoodViewModel(
    private val foodRepository: GetFoodDataRepository
): ViewModel() {

    private val _food = MutableStateFlow<List<FoodDataModel>>(emptyList())
    val food: StateFlow<List<FoodDataModel>> = _food
    init {
        getFoodData()
    }
    fun getFoodData(){
        viewModelScope.launch {
           try {
               val foods = foodRepository.getFoodData()
               _food.value = foods
           } catch (e: Exception) {
               // Log the error
           }
        }
    }
}
