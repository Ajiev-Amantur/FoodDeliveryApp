package com.example.fooddeliveryapp.presentation.mainScreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.data.CategoryDataModel
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
    private var allFoodOriginal = listOf<FoodDataModel>()

    private val _categories = MutableStateFlow<List<CategoryDataModel>>(emptyList())
    val categories: StateFlow<List<CategoryDataModel>> = _categories

    // Добавляем состояние выбранной категории
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
               _food.value = foods
               allFoodOriginal = foods
           } catch (e: Exception) {
               // Handle error
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
        _selectedCategory.value = categoryName // Обновляем выбранную категорию
        if (categoryName == "All"){
            _food.value = allFoodOriginal
        }else{
            _food.value = allFoodOriginal.filter { foodItem ->
                foodItem.name.contains(categoryName.removeSuffix("s"),
                    ignoreCase = true)
            }
        }
    }
}
