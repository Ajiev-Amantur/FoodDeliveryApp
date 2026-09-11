package com.example.fooddeliveryapp.domain.usecase

import com.example.fooddeliveryapp.domain.model.FoodDataModel

class GetFavoriteFoodUseCase {
    operator fun invoke(listFood: List<FoodDataModel>): List<FoodDataModel>{
        return listFood.filter { food ->
            food.isFavorite
        }
    }
}