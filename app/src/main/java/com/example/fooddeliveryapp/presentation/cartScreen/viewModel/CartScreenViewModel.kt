package com.example.fooddeliveryapp.presentation.cartScreen.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.data.local.entity.CartFoodEntity
import com.example.fooddeliveryapp.domain.model.CardDataModel
import com.example.fooddeliveryapp.domain.model.CartFoodModel
import com.example.fooddeliveryapp.domain.model.FoodDataModel
import com.example.fooddeliveryapp.domain.repository.CartFoodRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartScreenViewModel(
    private val cartFoodRepository: CartFoodRepository): ViewModel() {

    private var _cartFood = MutableStateFlow<List<CartFoodModel>>(emptyList())
    val cartFood: StateFlow<List<CartFoodModel>> = _cartFood

    private var _cardsData = MutableStateFlow<List<CardDataModel>>(emptyList())
    val cardsData: StateFlow<List<CardDataModel>> = _cardsData
    var cardName = mutableStateOf("")
    var textNameHolder = mutableStateOf("")
    var cardNumber = mutableStateOf("")
    var expireDateCard = mutableStateOf("")
    var CVC = mutableStateOf("")
    var totalPrice = mutableStateOf(0.0)
    fun addFoodCart(foodItem: FoodDataModel) {
        viewModelScope.launch {
            cartFoodRepository.addFoodCart(foodItem)
        }
    }

    init {
        loadCartFoods()
        loadCartFoods()
    }

    fun minusCount(foodItem: CartFoodModel) {
        viewModelScope.launch {
            cartFoodRepository.decreaseFoodCart(foodItem)
        }
    }

    fun deleteFood(foodItem: CartFoodModel) {
        viewModelScope.launch {
            cartFoodRepository.deleteFoodCart(foodItem)
        }
    }

    fun loadCartFoods() {
        viewModelScope.launch {
            cartFoodRepository.getCartFoods().collect { updatedList ->
                _cartFood.value = updatedList

            }
        }
    }

    fun addCard() {
        viewModelScope.launch {
            cartFoodRepository.addCard(
                CardDataModel(
                    id = 0,
                    cardName = cardName.value,
                    holderName = textNameHolder.value,
                    cardNumber = cardNumber.value,
                    dateCard = expireDateCard.value,
                    CVC = CVC.value
                )
            )
        }
    }

    fun loadCardsUserData() {
        viewModelScope.launch {
            cartFoodRepository.getAllDataCards().collect { dataModels ->
                _cardsData.value = dataModels
            }
        }

    }
}