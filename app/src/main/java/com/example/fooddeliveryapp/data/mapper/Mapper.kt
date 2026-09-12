package com.example.fooddeliveryapp.data.mapper

import com.example.fooddeliveryapp.data.local.model.FavoriteFoodEntity
import com.example.fooddeliveryapp.domain.model.FoodDataModel

fun FoodDataModel.toEntity(): FavoriteFoodEntity{
    return FavoriteFoodEntity(
        id = 0,
        name = this.name,
        image = this.image,
        price = this.price,
        description = this.description
    )
} 

fun FavoriteFoodEntity.toModel(): FoodDataModel{
    return FoodDataModel(
        name = this.name,
        image = this.image,
        price = this.price,
        description = this.description,
        isFavorite = true // Раз оно в этой базе,  значит оно точно в избранном
    )
}
