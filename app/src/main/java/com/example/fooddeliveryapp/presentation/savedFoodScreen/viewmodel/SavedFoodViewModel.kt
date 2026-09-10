package com.example.fooddeliveryapp.presentation.savedFoodScreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.data.FoodDataModel
import com.example.fooddeliveryapp.domain.repository.GetFoodDataRepository
import com.example.fooddeliveryapp.domain.usecase.GetFavoriteFoodUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SavedFoodViewModel(
    private val getFoodDataRepository: GetFoodDataRepository,
    private val getFavoriteFoodUseCase: GetFavoriteFoodUseCase
): ViewModel() {
    
    private var _favoriteFood = MutableStateFlow<List<FoodDataModel>>(emptyList())
    val favoriteFood: StateFlow<List<FoodDataModel>> = _favoriteFood

    init {
        loadAndFilterData()
    }
 
    fun loadAndFilterData(){
        viewModelScope.launch {
            val allFoods = getFoodDataRepository.getFoodData()
            _favoriteFood.value = getFavoriteFoodUseCase(allFoods)
        }
    }

    fun toggleFavorite(foodItem: FoodDataModel) {
        viewModelScope.launch {
            getFoodDataRepository.toggleFavorite(foodItem.name)
            loadAndFilterData() // Перезагружаем список
        }
    }
}
