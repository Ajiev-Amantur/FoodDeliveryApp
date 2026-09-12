package com.example.fooddeliveryapp.presentation.mainScreen.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.data.local.CategoryDataModel
import com.example.fooddeliveryapp.domain.model.FoodDataModel
import com.example.fooddeliveryapp.domain.repository.GetFoodDataRepository
import com.example.fooddeliveryapp.domain.usecase.FilterFoodByCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val foodRepository: GetFoodDataRepository,
    private val filtredFoodByCategory: FilterFoodByCategory
): ViewModel() {
// food models
    private val _food = MutableStateFlow<List<FoodDataModel>>(emptyList())
    val food: StateFlow<List<FoodDataModel>> = _food
    private var allFoodOriginal = listOf<FoodDataModel>()

    //food category data
    private val _categories = MutableStateFlow<List<CategoryDataModel>>(emptyList())
    val categories: StateFlow<List<CategoryDataModel>> = _categories

    // for filter food by category
    private val _selectedCategory = MutableStateFlow("All")
    val selectedCategory: StateFlow<String> = _selectedCategory
    // searchBar
    val searchQuery = mutableStateOf("")

    init {
        getFoodData()
        getCategories()
    }

    fun updateText(text: String){
        searchQuery.value = text
        if (text.isEmpty()){
            filterFood(_selectedCategory.value)
            }else {
            _food.value = allFoodOriginal.filter { foodItem ->
                foodItem.name.contains(text, ignoreCase = true)
            }
        }
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
            foodRepository.toggleFavorite(foodItem)
            getFoodData()
        }
    }
}
