package com.example.fooddeliveryapp.di

import androidx.room.Room
import com.example.fooddeliveryapp.data.local.database.CartFoodDataBase
import com.example.fooddeliveryapp.data.local.database.FavoriteFoodDataBase
import com.example.fooddeliveryapp.data.repository.FoodCartRepositoryImpl
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import com.example.fooddeliveryapp.data.repository.GetFoodDataRepositoryImpl
import com.example.fooddeliveryapp.domain.repository.CartFoodRepository
import com.example.fooddeliveryapp.domain.repository.GetFoodDataRepository
import com.example.fooddeliveryapp.domain.usecase.FilterFoodByCategory
import com.example.fooddeliveryapp.domain.usecase.GetFavoriteFoodUseCase
import com.example.fooddeliveryapp.presentation.cartScreen.viewModel.CartScreenViewModel
import com.example.fooddeliveryapp.presentation.detailsScreen.viewmodel.DetailScreenViewModel
import com.example.fooddeliveryapp.presentation.mainScreen.viewmodel.HomeViewModel
import com.example.fooddeliveryapp.presentation.savedFoodScreen.viewmodel.SavedFoodViewModel
import org.koin.android.ext.koin.androidContext

val appModule = module {
    // Репозитории
    single<GetFoodDataRepository> { GetFoodDataRepositoryImpl(get()) }
    single<CartFoodRepository> { FoodCartRepositoryImpl(get()) } // ДОБАВИЛИ

    // ViewModels
    viewModel { HomeViewModel(get(), FilterFoodByCategory()) }
    viewModel { SavedFoodViewModel(get(), GetFavoriteFoodUseCase()) }
    viewModel { DetailScreenViewModel(get()) } // ДОБАВИЛИ
    viewModel { CartScreenViewModel(get()) }   // ДОБАВИЛИ

    // Базы данных
    single {
        Room.databaseBuilder(
            androidContext(),
            FavoriteFoodDataBase::class.java,
            "favorite_food_db"
        ).build()
    }
    single { get<FavoriteFoodDataBase>().favoriteDao() }

    single {
        Room.databaseBuilder(
            androidContext(),
            CartFoodDataBase::class.java,
            "cart_db"
        ).build()
    }
    single { get<CartFoodDataBase>().cartDao() }
}