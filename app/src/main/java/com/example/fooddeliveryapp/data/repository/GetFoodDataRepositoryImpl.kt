package com.example.fooddeliveryapp.data.repository

import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.data.CategoryDataModel
import com.example.fooddeliveryapp.data.FoodDataModel
import com.example.fooddeliveryapp.domain.repository.GetFoodDataRepository

class GetFoodDataRepositoryImpl : GetFoodDataRepository {

    private var foodList = listOf(
        FoodDataModel("Beef Burger", R.drawable.ic_hamburger, "8.99", "Tasty beef burger with cheese"),
        FoodDataModel("Veggie Pizza", R.drawable.ic_pizza, "11.99", "Fresh vegetables and mozzarella"),
        FoodDataModel("Shawarma", R.drawable.ic_doner, "6.50", "Traditional lamb shawarma"),
        FoodDataModel("Coca Cola", R.drawable.ic_drinks, "2.50", "Original Taste 500ml"),
        FoodDataModel("Pasta Carbonara", R.drawable.ic_pasta, "12.99", "Classic italian pasta"),
        FoodDataModel("Sushi Set", R.drawable.ic_sushi, "15.00", "Premium sushi selection"),
        FoodDataModel("Kebab", R.drawable.ic_kebab, "9.00", "Grilled meat with spices"),
        FoodDataModel("Bakery Box", R.drawable.ic_bakery, "5.00", "Freshly baked croissants"),
        FoodDataModel("Cheese Burger", R.drawable.ic_hamburger, "9.50", "Double cheese special"),
        FoodDataModel("Pepperoni", R.drawable.ic_pizza, "13.00", "Spicy pepperoni pizza"),
        FoodDataModel("Chicken Doner", R.drawable.ic_doner, "7.00", "Grilled chicken wrap"),
        FoodDataModel("Orange Juice", R.drawable.ic_drinks, "3.00", "Freshly squeezed juice"),
        FoodDataModel("Pasta Pesto", R.drawable.ic_pasta, "11.00", "Pasta with fresh basil pesto"),
        FoodDataModel("Salmon Sushi", R.drawable.ic_sushi, "18.00", "Fresh salmon and avocado"),
        FoodDataModel("Shish Kebab", R.drawable.ic_kebab, "10.50", "Lamb shish kebab with onions"),
        FoodDataModel("Apple Pie", R.drawable.ic_bakery, "4.50", "Warm home-made apple pie")
    )

    override suspend fun getFoodData(): List<FoodDataModel> = foodList

    override suspend fun getCategories(): List<CategoryDataModel> = listOf(
        CategoryDataModel(1, "All", null),
        CategoryDataModel(2, "Burgers", R.drawable.ic_hamburger),
        CategoryDataModel(3, "Pizza", R.drawable.ic_pizza),
        CategoryDataModel(4, "Doner", R.drawable.ic_doner),
        CategoryDataModel(5, "Drinks", R.drawable.ic_drinks),
        CategoryDataModel(6, "Pasta", R.drawable.ic_pasta),
        CategoryDataModel(7, "Sushi", R.drawable.ic_sushi),
        CategoryDataModel(8, "Bakery", R.drawable.ic_bakery)
    )

    override suspend fun toggleFavorite(foodName: String) {
        foodList = foodList.map {
            if (it.name == foodName) it.copy(isFavorite = !it.isFavorite) else it
        }
    }
}
