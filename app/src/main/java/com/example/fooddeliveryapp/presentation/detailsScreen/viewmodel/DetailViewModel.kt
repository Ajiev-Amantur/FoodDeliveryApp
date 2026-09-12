package com.example.fooddeliveryapp.presentation.detailsScreen.viewmodel

import androidx.lifecycle.ViewModel
import com.example.fooddeliveryapp.domain.model.FoodDataModel
import kotlinx.coroutines.flow.MutableStateFlow

class DetailViewModel: ViewModel() {
    private var _foods= MutableStateFlow<List<FoodDataModel>>(emptyList())
    val foods : MutableStateFlow<List<FoodDataModel>> = _foods



}