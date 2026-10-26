package com.example.fooddeliveryapp.di

import androidx.room.Room
import com.example.fooddeliveryapp.data.local.database.CardDataDatabase
import com.example.fooddeliveryapp.data.local.database.CartFoodDataBase
import com.example.fooddeliveryapp.data.local.database.FavoriteFoodDataBase
import com.example.fooddeliveryapp.data.local.database.UserAddressesDatabase
import com.example.fooddeliveryapp.data.local.database.UserDatabase
import com.example.fooddeliveryapp.data.repository.AuthRepositoryImpl
import com.example.fooddeliveryapp.data.repository.FoodCartRepositoryImpl
import com.example.fooddeliveryapp.data.repository.GetFoodDataRepositoryImpl
import com.example.fooddeliveryapp.data.repository.UserAddressesRepositoryImpl
import com.example.fooddeliveryapp.domain.repository.AuthRepository
import com.example.fooddeliveryapp.domain.repository.CartFoodRepository
import com.example.fooddeliveryapp.domain.repository.GetFoodDataRepository
import com.example.fooddeliveryapp.domain.repository.UserAddressRepository
import com.example.fooddeliveryapp.domain.usecase.FilterFoodByCategory
import com.example.fooddeliveryapp.domain.usecase.GetFavoriteFoodUseCase
import com.example.fooddeliveryapp.presentation.cartScreen.viewModel.CartScreenViewModel
import com.example.fooddeliveryapp.presentation.detailsScreen.viewmodel.DetailScreenViewModel
import com.example.fooddeliveryapp.presentation.logInScreen.viewmodel.LogInViewModel
import com.example.fooddeliveryapp.presentation.mainScreen.viewmodel.HomeViewModel
import com.example.fooddeliveryapp.presentation.profileScreen.viewmodel.ProfileViewModel
import com.example.fooddeliveryapp.presentation.savedFoodScreen.viewmodel.SavedFoodViewModel
import com.example.fooddeliveryapp.presentation.signUpScreen.viewmodel.SignUpViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Репозитории
    single<GetFoodDataRepository> { GetFoodDataRepositoryImpl(get()) }
    single<CartFoodRepository> { FoodCartRepositoryImpl(get(), get()) }
    single<AuthRepository>{ AuthRepositoryImpl(get(), get()) }
    single<UserAddressRepository>{ UserAddressesRepositoryImpl(get()) }

    // ViewModels
    viewModel { HomeViewModel(get(), FilterFoodByCategory()) }
    viewModel { SavedFoodViewModel(get(), GetFavoriteFoodUseCase()) }
    viewModel { DetailScreenViewModel(get()) }
    viewModel { CartScreenViewModel(get()) }
    viewModel { LogInViewModel(get()) }
    viewModel { SignUpViewModel(get()) }
    viewModel { ProfileViewModel(get(), get()) }

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
    
    single { 
        Room.databaseBuilder(
            androidContext(),
            CardDataDatabase::class.java,
            "card_data_db"
        ).build()
    }
    single { get<CardDataDatabase>().cartDataDao() }

    single {
        Room.databaseBuilder(
            androidContext(),
            UserDatabase::class.java,
            "user_db"
        ).build()
    }
    single { get<UserDatabase>().dao() }

    single {
        Room.databaseBuilder(
            androidContext(),
            UserAddressesDatabase::class.java,
            "user_addresses_db"
        ).build()
    }
    single { get<UserAddressesDatabase>().dao() }
}
