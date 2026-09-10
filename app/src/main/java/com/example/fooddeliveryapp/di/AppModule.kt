package com.example.fooddeliveryapp.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import com.example.fooddeliveryapp.data.repository.GetFoodDataRepositoryImpl
import com.example.fooddeliveryapp.domain.repository.GetFoodDataRepository
import com.example.fooddeliveryapp.domain.usecase.FilterFoodByCategory
import com.example.fooddeliveryapp.domain.usecase.GetFavoriteFoodUseCase
import com.example.fooddeliveryapp.presentation.mainScreen.viewmodel.HomeViewModel
import com.example.fooddeliveryapp.presentation.savedFoodScreen.viewmodel.SavedFoodViewModel

val appModule = module {
    single<GetFoodDataRepository> { GetFoodDataRepositoryImpl() }
    viewModel { HomeViewModel(get<GetFoodDataRepository>(),
        FilterFoodByCategory()) }

    viewModel { SavedFoodViewModel(get<GetFoodDataRepository>(),
        GetFavoriteFoodUseCase()) }
}
