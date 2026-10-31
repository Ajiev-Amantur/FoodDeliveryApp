package com.example.fooddeliveryapp.presentation.profileScreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.domain.model.OrderHistoryModel
import com.example.fooddeliveryapp.domain.repository.OrderHistoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class OrderHistoryViewModel(
    private val orderHistoryFoodRepo: OrderHistoryRepository
): ViewModel() {

    private val _foodHistory = MutableStateFlow<List<OrderHistoryModel>>(emptyList())
    val foodHistory: StateFlow<List<OrderHistoryModel>> = _foodHistory.asStateFlow()

    init {
        getAllFoodsHistory()
    }

    fun getAllFoodsHistory() {
        viewModelScope.launch {
            orderHistoryFoodRepo.getAllFoodsHistory().collect { list ->
                _foodHistory.value = list
            }
        }
    }

    fun deleteOrderHistory(model: OrderHistoryModel) {
        viewModelScope.launch {
            orderHistoryFoodRepo.deleteFoodHistory(model)
        }
    }
}
