package com.example.fooddeliveryapp.presentation.mainScreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.data.CategoryDataModel
import com.example.fooddeliveryapp.data.FoodDataModel
import com.example.fooddeliveryapp.domain.repository.GetFoodDataRepository
import com.example.fooddeliveryapp.domain.usecase.FilterFoodByCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val foodRepository: GetFoodDataRepository,
    private val filtredFoodByCategory: FilterFoodByCategory
): ViewModel() {

    private val _food = MutableStateFlow<List<FoodDataModel>>(emptyList())
    val food: StateFlow<List<FoodDataModel>> = _food
    private var allFoodOriginal = listOf<FoodDataModel>()

    private val _categories = MutableStateFlow<List<CategoryDataModel>>(emptyList())
    val categories: StateFlow<List<CategoryDataModel>> = _categories

    private val _selectedCategory = MutableStateFlow("All")
    val selectedCategory: StateFlow<String> = _selectedCategory

    init {
        getFoodData()
        getCategories()
    }

    fun getFoodData(){
        viewModelScope.launch {
           try {
               val foods = foodRepository.getFoodData()
               allFoodOriginal = foods
               _food.value = filtredFoodByCategory(allFoodOriginal, _selectedCategory.value)
           } catch (e: Exception) {
               println(e)
           }
        }
    }

    fun getCategories() {
        viewModelScope.launch {
            try {
                val categories = foodRepository.getCategories()
                _categories.value = categories
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun filterFood(categoryName: String){
        _selectedCategory.value = categoryName
        _food.value = filtredFoodByCategory(allFoodOriginal, categoryName)
    }

    fun toggleFavorite(foodItem: FoodDataModel) {
        viewModelScope.launch {
            foodRepository.toggleFavorite(foodItem.name)
            getFoodData() // Refresh list to update UI
        }
    }
}
