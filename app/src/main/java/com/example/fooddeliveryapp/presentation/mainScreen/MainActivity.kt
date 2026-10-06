package com.example.fooddeliveryapp.presentation.mainScreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.fooddeliveryapp.HomeScreenUI
import com.example.fooddeliveryapp.OnboardingScreen
import com.example.fooddeliveryapp.presentation.LogInScreen.AuthManager
import com.example.fooddeliveryapp.presentation.LogInScreen.ForgotPasswordScreen
import com.example.fooddeliveryapp.presentation.LogInScreen.LogInScreen
import com.example.fooddeliveryapp.presentation.LogInScreen.VerificationScreen
import com.example.fooddeliveryapp.presentation.cartScreen.AddCardScreen
import com.example.fooddeliveryapp.presentation.cartScreen.CartScreen
import com.example.fooddeliveryapp.presentation.cartScreen.PaymentScreen
import com.example.fooddeliveryapp.presentation.cartScreen.SucsessAddedCard
import com.example.fooddeliveryapp.presentation.cartScreen.viewModel.CartScreenViewModel
import com.example.fooddeliveryapp.presentation.detailsScreen.FoodDetailsScreen
import com.example.fooddeliveryapp.presentation.detailsScreen.viewmodel.DetailScreenViewModel
import com.example.fooddeliveryapp.presentation.mainScreen.viewmodel.HomeViewModel
import com.example.fooddeliveryapp.presentation.savedFoodScreen.SavedFoodScreen
import com.example.fooddeliveryapp.presentation.savedFoodScreen.viewmodel.SavedFoodViewModel
import com.example.fooddeliveryapp.presentation.signUpScreen.SignUpScreen
import com.example.fooddeliveryapp.presentation.startScreen.OnBoardingPref
import com.example.fooddeliveryapp.presentation.welcomeStartScreen.WelcomeStartScreen
import com.example.fooddeliveryapp.ui.theme.FoodDeliveryAppTheme
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val onBoardingPref = OnBoardingPref(this)
        val startDestination = if (onBoardingPref.onBoardingCompleted()) {
            OnboardingRoute
        } else {
            WelcomeRoute
        }

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
                    startDestination = startDestination
                ) {
                    composable<WelcomeRoute> {
                        WelcomeStartScreen(
                            onFinishClick = {
                                onBoardingPref.setOnBoardingCompleted()
                                navController.navigate(OnboardingRoute) {
                                    popUpTo(WelcomeRoute) { inclusive = true }
                                }
                            }
                        )
                    }
                    composable<OnboardingRoute> {
                        OnboardingScreen(
                            onLoginClick = {
                                navController.navigate(LoginRoute) {
                                    popUpTo(OnboardingRoute) { inclusive = true }
                                }
                            },
                            onSignUpClick = {navController.navigate(SignUpRoute)}
                        )
                    }
                    composable<LoginRoute> {
                        val context = LocalContext.current
                        val authManager = remember { AuthManager(context)}
                        val coroutineScope = rememberCoroutineScope()
                        LogInScreen(
                            onBackClick = { navController.popBackStack() },
                            onLogInClick = { navController.navigate(HomeRoute) },
                            onSignUpClick = { navController.navigate(SignUpRoute) },
                            onForgotPasswordClick = { navController.navigate(ForgotPasswordRoute) },
                            onGoogleClick = {
                                coroutineScope.launch {
                                    val sucsess = authManager.signInWithGoogle()
                                    if (sucsess) {
                                        navController.navigate(HomeRoute) {
                                            popUpTo(LoginRoute) { inclusive = true }
                                        }
                                    }
                                }
                            },
                            onGuestClick = { navController.navigate(HomeRoute) }
                        )
                    }
                    composable<SignUpRoute> {
                        SignUpScreen(
                            onBackClick = { navController.popBackStack() },
                            onSignUpClick = { navController.navigate(HomeRoute) }
                        )
                    }
                    composable<ForgotPasswordRoute> {
                        ForgotPasswordScreen(
                            onBackClick = { navController.popBackStack() },
                            onSendCodeClick = { navController.navigate(VerificationRoute) }
                        )
                    }
                    composable<VerificationRoute> {
                        VerificationScreen(
                            onBackClick = { navController.popBackStack() },
                            onVerifyClick = { code ->
                                navController.navigate(HomeRoute) {
                                    popUpTo(LoginRoute) { inclusive = true }
                                }
                            }
                        )
                    }
                    composable<HomeRoute> {
                        val homeViewModel: HomeViewModel = koinViewModel()
                        HomeScreenUI(
                            isSelectedHome = isSelectedHomeScreen,
                            isSelectedSaved = isSelectedSavedScreen,
                            isSelectedCart = isSelectedCartScreen,
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
                        SavedFoodScreen(
                            isSelectedHome =  isSelectedHomeScreen,
                            isSelectedSaved = isSelectedSavedScreen,
                            isSelectedCart = isSelectedCartScreen,
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
                            },
                            onFavoriteClick = { navController.navigate(SavedFoodRoute) },
                            onCartClick = { navController.navigate(CartRoute)},
                            onHomeClick = { navController.navigate(HomeRoute)},
                            onBackClick = {navController.popBackStack()}
                        )
                    }
                    composable<CartRoute> { navBackEntry ->
                        val cartViewModel: CartScreenViewModel = koinViewModel(viewModelStoreOwner =navBackEntry )
                        CartScreen(
                            cartScreenViewModel = cartViewModel,
                            onBackClick = {navController.popBackStack()},
                            onPaymentClick = {navController.navigate(PaymentRoute)}
                        )
                    }
                    composable<PaymentRoute>{ navBackEntry ->
                        var cartEntry = remember(navBackEntry) {
                            navController.getBackStackEntry<CartRoute>()
                        }
                        val cartScreenViewModel: CartScreenViewModel = koinViewModel(viewModelStoreOwner =cartEntry)
                        PaymentScreen(
                            onBackClick = {navController.popBackStack()},
                            onAddCardClick = { cardName ->
                                navController.navigate(AddCardRoute(cardName))
                            },
                            onConfirmClick = {navController.navigate(SuccessRoute)},
                            cartScreenViewModel = cartScreenViewModel
                        )
                    }
                    composable<AddCardRoute>{ backStackEntity ->
                        val route: AddCardRoute = backStackEntity.toRoute()
                        val cardName = route.cardName
                        val cartScreenViewModel: CartScreenViewModel = koinViewModel()
                        AddCardScreen(
                            nameCard = cardName,
                            onBackClick = {navController.popBackStack()},
                            cartScreenViewModel = cartScreenViewModel,
                            onAddClick = {navController.navigate(SucsessCardAdded)}
                        )
                    }
                    composable<SuccessRoute> {
                        com.example.fooddeliveryapp.presentation.cartScreen.SuccessScreen(
                            onTrackOrderClick = {
                                navController.navigate(HomeRoute) {
                                    popUpTo(HomeRoute) { inclusive = true }
                                }
                            }
                        )
                    }
                    composable<SucsessCardAdded>{
                        SucsessAddedCard(
                            onClick = {navController.navigate(PaymentRoute)}
                        )
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
