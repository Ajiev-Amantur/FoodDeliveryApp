package com.example.fooddeliveryapp.data.repository

import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.data.FoodDataModel
import com.example.fooddeliveryapp.domain.repository.GetFoodDataRepository

class GetFoodDataRepositoryImpl : GetFoodDataRepository {
    override suspend fun getFoodData(): List<FoodDataModel> {
        return listOf(
            FoodDataModel("hamburger",R.drawable.hamburger,"8.99","Tasty Hamburger!",),
            FoodDataModel("Veggie Pizza", R.drawable.pizza, "10.99", "Lorem ipsum dolor..."),
            FoodDataModel("hamburger",R.drawable.hamburger,"8.99","Tasty Hamburger!",),
            FoodDataModel("Veggie Pizza", R.drawable.pizza, "10.99", "Lorem ipsum dolor..."),
            FoodDataModel("hamburger",R.drawable.hamburger,"8.99","Tasty Hamburger!",),
            FoodDataModel("Veggie Pizza", R.drawable.pizza, "10.99", "Lorem ipsum dolor..."),
            FoodDataModel("hamburger",R.drawable.hamburger,"8.99","Tasty Hamburger!",),
            FoodDataModel("Veggie Pizza", R.drawable.pizza, "10.99", "Lorem ipsum dolor..."),
            FoodDataModel("hamburger",R.drawable.hamburger,"8.99","Tasty Hamburger!",),
            FoodDataModel("Veggie Pizza", R.drawable.pizza, "10.99", "Lorem ipsum dolor..."),
            FoodDataModel("hamburger",R.drawable.hamburger,"8.99","Tasty Hamburger!",),
            FoodDataModel("Veggie Pizza", R.drawable.pizza, "10.99", "Lorem ipsum dolor..."),
        )
    }
}