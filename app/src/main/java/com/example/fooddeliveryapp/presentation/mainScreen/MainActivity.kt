package com.example.fooddeliveryapp.presentation.mainScreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.fooddeliveryapp.HomeScreenUI
import com.example.fooddeliveryapp.OnboardingScreen
import com.example.fooddeliveryapp.presentation.cartScreen.CartScreen
import com.example.fooddeliveryapp.presentation.cartScreen.viewModel.CartScreenViewModel
import com.example.fooddeliveryapp.presentation.detailsScreen.FoodDetailsScreen
import com.example.fooddeliveryapp.presentation.detailsScreen.viewmodel.DetailScreenViewModel
import com.example.fooddeliveryapp.presentation.mainScreen.viewmodel.HomeViewModel
import com.example.fooddeliveryapp.presentation.savedFoodScreen.SelectedFoodScreen
import com.example.fooddeliveryapp.presentation.savedFoodScreen.viewmodel.SavedFoodViewModel
import com.example.fooddeliveryapp.ui.theme.FoodDeliveryAppTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodDeliveryAppTheme {
                val navController = rememberNavController()

                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                val isSelectedCartScreen = currentDestination?.hasRoute<CartRoute>() == true
                val isSelectedHomeScreen = currentDestination?.hasRoute<HomeRoute>() == true
                val isSelectedSavedScreen = currentDestination?.hasRoute<SavedFoodRoute>() == true

                NavHost(
                    navController = navController,
                    startDestination = OnboardingRoute
                ) {
                    composable<OnboardingRoute> {
                        OnboardingScreen(
                            onLoginClick = {
                                navController.navigate(HomeRoute)
                            }
                        )
                    }
                    composable<HomeRoute> {
                        val homeViewModel: HomeViewModel = koinViewModel()
                        HomeScreenUI(
                            isSelectedHomeScreen,
                            homeViewModel = homeViewModel,
                            onFavoriteNavClick = { navController.navigate(SavedFoodRoute) },
                            onFoodClick = { foodItem ->
                                navController.navigate(
                                    DetailRoute(
                                        name = foodItem.name,
                                        image = foodItem.image,
                                        price = foodItem.price,
                                        description = foodItem.description,
                                        isFavorite = foodItem.isFavorite
                                    )
                                )
                            },
                            onCartClick = { navController.navigate(CartRoute) }
                        )
                    }
                    composable<SavedFoodRoute> {
                        val savedViewModel: SavedFoodViewModel = koinViewModel()
                        SelectedFoodScreen(
                            isSelectedSavedScreen,
                            savedFoodViewModel = savedViewModel,
                            onFoodClick = { foodItem ->
                                navController.navigate(
                                    DetailRoute(
                                        name = foodItem.name,
                                        image = foodItem.image,
                                        price = foodItem.price,
                                        description = foodItem.description,
                                        isFavorite = foodItem.isFavorite
                                    )
                                )
                            }
                        )
                    }
                    composable<CartRoute> {
                        val cartViewModel: CartScreenViewModel = koinViewModel()
                        CartScreen(
                            isSelectedCartScreen,
                            cartViewModel,
                            onBackClick = {navController.popBackStack()})
                    }
                    composable<DetailRoute> { backStackEntry ->
                        val homeViewModel: HomeViewModel = koinViewModel()
                        val route: DetailRoute = backStackEntry.toRoute()
                        
                        val foodItem = com.example.fooddeliveryapp.domain.model.FoodDataModel(
                            name = route.name,
                            image = route.image,
                            price = route.price,
                            description = route.description,
                            isFavorite = route.isFavorite
                        )
                        val detailViewModel: DetailScreenViewModel = koinViewModel()
                        FoodDetailsScreen(
                            foodDataModel = foodItem,
                            onBackClick = { navController.popBackStack() },
                            onFavoriteClick = { homeViewModel.toggleFavorite(foodItem) },
                            onAddCartClick = { model ->
                                detailViewModel.addCartFood(model)
                            }
                        )
                    }
                }
            }
        }
    }
}
