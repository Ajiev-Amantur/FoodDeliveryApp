package com.example.fooddeliveryapp.presentation.mainScreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.fooddeliveryapp.HomeScreenUI
import com.example.fooddeliveryapp.OnboardingScreen
import com.example.fooddeliveryapp.presentation.logInScreen.AuthManager
import com.example.fooddeliveryapp.presentation.logInScreen.ForgotPasswordScreen
import com.example.fooddeliveryapp.presentation.logInScreen.LogInScreen
import com.example.fooddeliveryapp.presentation.logInScreen.VerificationScreen
import com.example.fooddeliveryapp.presentation.cartScreen.AddCardScreen
import com.example.fooddeliveryapp.presentation.cartScreen.CartScreen
import com.example.fooddeliveryapp.presentation.cartScreen.PaymentScreen
import com.example.fooddeliveryapp.presentation.cartScreen.SucsessAddedCard
import com.example.fooddeliveryapp.presentation.cartScreen.viewModel.CartScreenViewModel
import com.example.fooddeliveryapp.presentation.detailsScreen.FoodDetailsScreen
import com.example.fooddeliveryapp.presentation.detailsScreen.viewmodel.DetailScreenViewModel
import com.example.fooddeliveryapp.presentation.logInScreen.viewmodel.LogInViewModel
import com.example.fooddeliveryapp.presentation.mainScreen.viewmodel.HomeViewModel
import com.example.fooddeliveryapp.presentation.profileScreen.EditProfileScreen
import com.example.fooddeliveryapp.presentation.profileScreen.PersonalInfoScreen
import com.example.fooddeliveryapp.presentation.profileScreen.ProfileScreen
import com.example.fooddeliveryapp.presentation.profileScreen.viewmodel.ProfileViewModel
import com.example.fooddeliveryapp.presentation.savedFoodScreen.SavedFoodScreen
import com.example.fooddeliveryapp.presentation.savedFoodScreen.viewmodel.SavedFoodViewModel
import com.example.fooddeliveryapp.presentation.signUpScreen.SignUpScreen
import com.example.fooddeliveryapp.presentation.signUpScreen.viewmodel.SignUpViewModel
import com.example.fooddeliveryapp.presentation.startScreen.OnBoardingPref
import com.example.fooddeliveryapp.presentation.welcomeStartScreen.WelcomeStartScreen
import com.example.fooddeliveryapp.ui.theme.DarkBackground
import com.example.fooddeliveryapp.ui.theme.FoodDeliveryAppTheme
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(
                android.graphics.Color.GREEN
            )
        )
        val currentUser = FirebaseAuth.getInstance().currentUser

        val onBoardingPref = OnBoardingPref(this)
        val startDestination = if (currentUser!= null){
            HomeRoute
        }
            else if (onBoardingPref.onBoardingCompleted()) {
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
                    modifier = Modifier.background(DarkBackground),
                    navController = navController,
                    startDestination = startDestination,
                    enterTransition = { slideInHorizontally(initialOffsetX = { it / 4 }) + fadeIn(animationSpec = tween(300)) },
                    exitTransition = { slideOutHorizontally(targetOffsetX = { -it / 4 }) + fadeOut(animationSpec = tween(300)) },
                    popEnterTransition = { slideInHorizontally(initialOffsetX = { -it / 4 }) + fadeIn(animationSpec = tween(300)) },
                    popExitTransition = { slideOutHorizontally(targetOffsetX = { it / 4 }) + fadeOut(animationSpec = tween(300)) }
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
                        val logInViewModel: LogInViewModel = koinViewModel()

                        LogInScreen(
                            logInViewModel = logInViewModel,
                            onBackClick = { navController.popBackStack() },
                            onLogInClick = { navController.navigate(HomeRoute) },
                            onSignUpClick = { navController.navigate(SignUpRoute) },
                            onForgotPasswordClick = { navController.navigate(ForgotPasswordRoute) },
                            onGoogleClick = {
                                logInViewModel.signInViewModel("", "") { isSuccess ->
                                    if (isSuccess) {
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
                        val signUpViewModel: SignUpViewModel = koinViewModel()
                        SignUpScreen(
                            signUpViewModel = signUpViewModel,
                            onBackClick = { navController.popBackStack() },
                            onSignUpClick = { navController.navigate(HomeRoute) }
                        )
                    }
                    composable<ForgotPasswordRoute> {
                        val logInViewModel: LogInViewModel = koinViewModel()
                        ForgotPasswordScreen(
                            onBackClick = { navController.popBackStack() },
                            onSendCodeClick = { code,email ->
                                navController.navigate(VerificationRoute(code,email))
                            },
                            logInViewModel = logInViewModel
                        )
                    }
                    composable<VerificationRoute> { backStackEntry ->
                        val route: VerificationRoute = backStackEntry.toRoute()
                        VerificationScreen(
                            code = route.code,
                            userEmail = route.email,
                            onBackClick = { navController.popBackStack() },
                            onVerifyClick = {
                                navController.navigate(OnboardingRoute) {
                                    popUpTo(LoginRoute) { inclusive = true }
                                }
                            }
                        )
                    }
                    composable<HomeRoute> {
                        val homeViewModel: HomeViewModel = koinViewModel()
                        HomeScreenUI(
                            onProfileClick = { navController.navigate(ProfileRoute) },
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
                            onProfileClick = { navController.navigate(ProfileRoute) },
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
                    composable<ProfileRoute> {
                        val isSelectedProfileScreen = currentDestination?.hasRoute<ProfileRoute>() == true
                        val auth = remember { FirebaseAuth.getInstance() }
                        val profileViewModel: ProfileViewModel = koinViewModel()
                        val userDataState by profileViewModel.userData.collectAsState()

                        ProfileScreen(
                            userName = userDataState?.name ?: "User",
                            userEmail = userDataState?.email ?: "user@fooddelivery.com",
                            userPhotoUrl = userDataState?.photoUrl,
                            isSelectedHome = isSelectedHomeScreen,
                            isSelectedSaved = isSelectedSavedScreen,
                            isSelectedCart = isSelectedCartScreen,
                            isSelectedProfile = isSelectedProfileScreen,
                            onBackClick = { navController.popBackStack() },
                            onHomeClick = { navController.navigate(HomeRoute) },
                            onFavoriteClick = { navController.navigate(SavedFoodRoute) },
                            onCartClick = { navController.navigate(CartRoute) },
                            onPaymentMethodsClick = { navController.navigate(PaymentRoute) },
                            onPersonalInfoClick = { navController.navigate(PersonalInfoRote) },
                            onLogOutClick = {
                                auth.signOut()
                                navController.navigate(OnboardingRoute) {
                                    popUpTo(HomeRoute) { inclusive = true }
                                }
                            }
                        )
                    }
                    composable<PersonalInfoRote> {
                        val profileViewModel: ProfileViewModel = koinViewModel()
                        val userDataState by profileViewModel.userData.collectAsState()

                        PersonalInfoScreen(
                            profileViewModel = profileViewModel,
                            fullName = userDataState?.name ?: "Vishal Khadok",
                            email = userDataState?.email ?: "hello@halallab.co",
                            phone = userDataState?.phone ?: "408-841-0926",
                            bio = userDataState?.bio ?: "I love fast food",
                            userPhotoUrl = userDataState?.photoUrl,
                            onBackClick = { navController.popBackStack() },
                            onEditClick = { navController.navigate(EdipProfileRoute) }
                        )
                    }
                    composable<EdipProfileRoute> {
                        val profileViewModel: ProfileViewModel = koinViewModel()
                        val userDataState by profileViewModel.userData.collectAsState()

                        EditProfileScreen(
                            profileViewModel = profileViewModel,
                            initialFullName = userDataState?.name ?: "Vishal Khadok",
                            initialEmail = userDataState?.email ?: "hello@halallab.co",
                            initialPhone = userDataState?.phone ?: "408-841-0926",
                            initialBio = userDataState?.bio ?: "I love fast food",
                            userPhotoUrl = userDataState?.photoUrl,
                            onBackClick = { navController.popBackStack() },
                            onSaveClick = { fullName, email, phone, bio ->
                                profileViewModel.saveUserData(fullName, email, phone, bio)
                                navController.popBackStack()
                            }
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
