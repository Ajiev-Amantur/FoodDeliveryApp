package com.example.fooddeliveryapp.data.mapper

import com.example.fooddeliveryapp.data.local.entity.CardDataEntity
import com.example.fooddeliveryapp.data.local.entity.CartFoodEntity
import com.example.fooddeliveryapp.data.local.entity.FavoriteFoodEntity
import com.example.fooddeliveryapp.domain.model.CardDataModel
import com.example.fooddeliveryapp.domain.model.CartFoodModel
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

fun CartFoodModel.toEntity(): CartFoodEntity{
    return CartFoodEntity(
        id = this.id,
        name = this.name,
        price = this.price,
        image = this.image,
        quantity = this.quantity
    )
}
fun CartFoodEntity.toModel(): CartFoodModel{
    return CartFoodModel(
        id = this.id,
        name = this.name,
        price = this.price,
        image = this.image,
        quantity = this.quantity
    )
}

fun CardDataEntity.toModel(): CardDataModel{
    return CardDataModel(
        id = this.id,
        cardName = this.cardName,
        holderName =  this.holderName,
        cardNumber = this.cardNumber,
        dateCard = this.dateCard,
        CVC = this.CVC

    )
}

fun CardDataModel.toEntity(): CardDataEntity{
    return CardDataEntity(
        id = this.id,
        cardName = this.cardName,
        holderName =  this.holderName,
        cardNumber = this.cardNumber,
        dateCard = this.dateCard,
        CVC = this.CVC
    )
}