package com.example.fooddeliveryapp.data.mapper

import com.example.fooddeliveryapp.data.local.entity.AddressEntity
import com.example.fooddeliveryapp.data.local.entity.CardDataEntity
import com.example.fooddeliveryapp.data.local.entity.CartFoodEntity
import com.example.fooddeliveryapp.data.local.entity.FavoriteFoodEntity
import com.example.fooddeliveryapp.data.local.entity.OrderHistoryEntity
import com.example.fooddeliveryapp.data.local.entity.UserDataEntity
import com.example.fooddeliveryapp.domain.model.AddressModel
import com.example.fooddeliveryapp.domain.model.CardDataModel
import com.example.fooddeliveryapp.domain.model.CartFoodModel
import com.example.fooddeliveryapp.domain.model.FoodDataModel
import com.example.fooddeliveryapp.domain.model.OrderHistoryModel
import com.example.fooddeliveryapp.domain.model.UserDataModel

fun FoodDataModel.toEntity(): FavoriteFoodEntity {
    return FavoriteFoodEntity(
        id = 0,
        name = this.name,
        image = this.image,
        price = this.price,
        description = this.description
    )
} 

fun FavoriteFoodEntity.toModel(): FoodDataModel {
    return FoodDataModel(
        name = this.name,
        image = this.image,
        price = this.price,
        description = this.description,
        isFavorite = true
    )
}

fun CartFoodModel.toEntity(): CartFoodEntity {
    return CartFoodEntity(
        id = this.id,
        name = this.name,
        price = this.price,
        image = this.image,
        quantity = this.quantity
    )
}

fun CartFoodEntity.toModel(): CartFoodModel {
    return CartFoodModel(
        id = this.id,
        name = this.name,
        price = this.price,
        image = this.image,
        quantity = this.quantity
    )
}

fun CardDataEntity.toModel(): CardDataModel {
    return CardDataModel(
        id = this.id,
        cardName = this.cardName,
        holderName = this.holderName,
        cardNumber = this.cardNumber,
        dateCard = this.dateCard,
        CVC = this.CVC
    )
}

fun CardDataModel.toEntity(): CardDataEntity {
    return CardDataEntity(
        id = this.id,
        cardName = this.cardName,
        holderName = this.holderName,
        cardNumber = this.cardNumber,
        dateCard = this.dateCard,
        CVC = this.CVC
    )
}

fun UserDataModel.toEntity(): UserDataEntity {
    return UserDataEntity(
        id = 1,
        name = this.name,
        email = this.email,
        photoUrl = this.photoUrl,
        phone = this.phone,
        bio = this.bio
    )
}

fun UserDataEntity.toModel(): UserDataModel {
    return UserDataModel(
        name = this.name,
        email = this.email,
        photoUrl = this.photoUrl,
        phone = this.phone,
        bio = this.bio
    )
}

fun AddressModel.toEntity(): AddressEntity {
    return AddressEntity(
        id = this.id,
        address = this.address,
        street = this.street,
        postCode = this.postCode,
        appartment = this.appartment,
        label = this.label
    )
}

fun AddressEntity.toModel(): AddressModel {
    return AddressModel(
        id = this.id,
        address = this.address,
        street = this.street,
        postCode = this.postCode,
        appartment = this.appartment,
        label = this.label
    )
}

fun OrderHistoryModel.toEntity(): OrderHistoryEntity {
    return OrderHistoryEntity(
        id = 0,
        orderId = this.orderId,
        date = this.date,
        status = this.status,
        itemsSummary = this.itemsSummary,
        itemCount = this.itemCount,
        totalPrice = this.totalPrice
    )
}

fun OrderHistoryEntity.toModel(): OrderHistoryModel {
    return OrderHistoryModel(
        orderId = this.orderId,
        date = this.date,
        status = this.status,
        itemsSummary = this.itemsSummary,
        itemCount = this.itemCount,
        totalPrice = this.totalPrice
    )
}
