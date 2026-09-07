package com.example.fooddeliveryapp.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import com.example.fooddeliveryapp.data.repository.GetFoodDataRepositoryImpl
import com.example.fooddeliveryapp.domain.repository.GetFoodDataRepository
import com.example.fooddeliveryapp.presentation.mainScreen.viewmodel.FoodViewModel

val appModule = module {
    single<GetFoodDataRepository> { GetFoodDataRepositoryImpl() }
    viewModel { FoodViewModel(get<GetFoodDataRepository>()) }
}
