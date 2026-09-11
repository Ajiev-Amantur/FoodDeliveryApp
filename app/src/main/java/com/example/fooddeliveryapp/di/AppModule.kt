package com.example.fooddeliveryapp.di

import androidx.room.Room
import com.example.fooddeliveryapp.data.FavoriteFoodDataBase
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import com.example.fooddeliveryapp.data.repository.GetFoodDataRepositoryImpl
import com.example.fooddeliveryapp.domain.repository.GetFoodDataRepository
import com.example.fooddeliveryapp.domain.usecase.FilterFoodByCategory
import com.example.fooddeliveryapp.domain.usecase.GetFavoriteFoodUseCase
import com.example.fooddeliveryapp.presentation.mainScreen.viewmodel.HomeViewModel
import com.example.fooddeliveryapp.presentation.savedFoodScreen.viewmodel.SavedFoodViewModel
import org.koin.android.ext.koin.androidContext

val appModule = module {
    single<GetFoodDataRepository> { GetFoodDataRepositoryImpl(get()) }
    viewModel { HomeViewModel(get<GetFoodDataRepository>(),
        FilterFoodByCategory()) }

    viewModel { SavedFoodViewModel(get<GetFoodDataRepository>(),
        GetFavoriteFoodUseCase()) }

    single {
        Room.databaseBuilder(
            androidContext(),
            FavoriteFoodDataBase::class.java,
            "favorite_food_db",
        ).build()

    }
    single { get<FavoriteFoodDataBase>().favoriteDao() }

}
