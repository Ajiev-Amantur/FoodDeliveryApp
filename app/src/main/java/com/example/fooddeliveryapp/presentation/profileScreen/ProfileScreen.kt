package com.example.fooddeliveryapp.presentation.profileScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.presentation.components.CustomBottomNavigation
import com.example.fooddeliveryapp.presentation.profileScreen.viewmodel.ProfileViewModel
import com.example.fooddeliveryapp.ui.theme.DarkBackground
import com.example.fooddeliveryapp.ui.theme.GradientEnd
import com.example.fooddeliveryapp.ui.theme.GradientStart
import org.jetbrains.annotations.Async

@Composable
fun ProfileScreen(
    userName: String = "John Doe",
    userEmail: String = "example@gmail.com",
    userPhotoUrl: String? = null,
    isSelectedHome: Boolean = false,
    isSelectedSaved: Boolean = false,
    isSelectedCart: Boolean = false,
    isSelectedProfile: Boolean = true,
    onBackClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {},
    onCartClick: () -> Unit = {},
    onPaymentMethodsClick: () -> Unit = {},
    onLogOutClick: () -> Unit = {}
) {
    val gradient = Brush.horizontalGradient(listOf(GradientStart, GradientEnd))

    Scaffold(
        bottomBar = {
            CustomBottomNavigation(
                onHomeClick = onHomeClick,
                onFavoriteClick = onFavoriteClick,
                onCartClick = onCartClick,
                onProfileClick = {},
                isSelectedHome = isSelectedHome,
                isSelectedSaved = isSelectedSaved,
                isSelectedCart = isSelectedCart,
                isSelectedProfile = isSelectedProfile
            )
        },
        containerColor = Color.Transparent
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .paint(
                    painter = painterResource(id = R.drawable.ic_food_fon),
                    contentScale = ContentScale.Crop
                )
        ) {
            Column(modifier = Modifier.fillMaxSize()) {

                // 1. ВЕРХНЯЯ ШАПКА С АВАТАРКОЙ И ИМЕНЕМ
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 20.dp)
                ) {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier
                            .size(45.dp)
                            .align(Alignment.Start)
                            .clip(CircleShape)
                            .background(Color.White)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Круглая аватарка пользователя
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                    ) {
                        if (userPhotoUrl != null) {
                            AsyncImage(
                                model = userPhotoUrl,
                                contentDescription = "User Avatar",
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Avatar",
                                tint = Color(0xFFFF7622),
                                modifier = Modifier.size(50.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Имя пользователя
                    Text(
                        text = userName,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    // Email пользователя
                    Text(
                        text = userEmail,
                        fontSize = 14.sp,
                        color = Color.White.copy(alpha = 0.8f)
                    )
                }

                // 2. НИЖНЯЯ ТЕМНАЯ КАРТОЧКА СО СПИСКОМ НАСТРОЕК
                Surface(
                    color = DarkBackground,
                    shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        // Опции меню
                        ProfileMenuItem(
                            icon = Icons.Default.Person,
                            title = "Personal Info",
                            onClick = {}
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        ProfileMenuItem(
                            icon = Icons.Default.LocationOn,
                            title = "Addresses",
                            onClick = {}
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        ProfileMenuItem(
                            icon = Icons.Default.ShoppingCart,
                            title = "Payment Methods",
                            onClick = onPaymentMethodsClick
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        ProfileMenuItem(
                            icon = Icons.Default.Home,
                            title = "Order History",
                            onClick = {}
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        ProfileMenuItem(
                            icon = Icons.Default.Lock,
                            title = "Change Password",
                            onClick = {}
                        )

                        Spacer(modifier = Modifier.height(28.dp))

                        // КНОПКА ВЫХОДА (LOG OUT)
                        Button(
                            onClick = onLogOutClick,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp)
                                .clip(RoundedCornerShape(14.dp))
                                .background(gradient),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent
                            )
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                                    contentDescription = "Log Out",
                                    tint = Color.White,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "LOG OUT",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileMenuItem(
    icon: ImageVector,
    title: String,
    onClick: () -> Unit = {}
) {
    Surface(
        color = Color(0xFF262835),
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF333544))
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = title,
                        tint = Color(0xFFFF7622),
                        modifier = Modifier.size(20.dp)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }

            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(
        userName = "John Doe",
        userEmail = "example@gmail.com",
        userPhotoUrl = null,
        isSelectedProfile = true
    )
}
