package com.example.fooddeliveryapp.domain.usecase

import com.example.fooddeliveryapp.data.FoodDataModel

class FilterFoodByCategory {
      operator fun invoke(allFoods: List<FoodDataModel>, categoryName: String): List<FoodDataModel> {
        if (categoryName == "All") return allFoods

        val searchTerm = categoryName.removeSuffix("s")
        
        return allFoods.filter { food ->
            food.name.contains(searchTerm, ignoreCase = true)
        }
    }
}
