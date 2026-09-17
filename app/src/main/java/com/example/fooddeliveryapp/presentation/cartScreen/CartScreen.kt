package com.example.fooddeliveryapp.presentation.cartScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.domain.model.FoodDataModel
import com.example.fooddeliveryapp.presentation.cartScreen.viewModel.CartScreenViewModel
import com.example.fooddeliveryapp.ui.theme.GradientEnd
import com.example.fooddeliveryapp.ui.theme.GradientStart

@Composable
fun CartScreen(
    isSelectedHome: Boolean,
    isSelectedSaved: Boolean,
    isSelectedCart: Boolean,
    cartScreenViewModel: CartScreenViewModel,
    onBackClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    onCartClick: () -> Unit,
    onHomeClick: () -> Unit
) {
    val gradient = Brush.horizontalGradient(listOf(GradientStart, GradientEnd))
    val foods by cartScreenViewModel.cartFood.collectAsState()
    Scaffold(
        containerColor = Color(0xFF121223), // Темно-синий/черный фон как на фото
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Кнопка назад
                IconButton(
                    onClick = { onBackClick() },
                    modifier = Modifier
                        .size(45.dp)
                        .background(Color(0xFF2B2B3D), CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = Color.White
                    )
                }

                Text(
                    text = "Cart",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )

                TextButton(onClick = { /* Готово */ }) {
                    Text(
                        text = "DONE",
                        color = Color(0xFF00C569), // Зеленый как на фото
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        },
        bottomBar = {
            Column(modifier = Modifier.background(Color(0xFF121223))) {
                // Кнопка оформления заказа внизу
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                        .height(60.dp)
                        .background(gradient, RoundedCornerShape(20.dp))
                        .clip(RoundedCornerShape(20.dp))
                        .clickable { /* Checkout action */ },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Checkout",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                CustomBottomNavigation(
                    onFavoriteClick = { onFavoriteClick() },
                    onCartClick = { onCartClick() },
                    onHomeClick = { onHomeClick() },
                    isSelectedHome = isSelectedHome,
                    isSelectedSaved = isSelectedSaved,
                    isSelectedCart = isSelectedCart
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            contentPadding = PaddingValues(bottom = 20.dp)
        ) {
            // Тестовые элементы корзины

            items(items = foods) { food ->
                CartItem(
                    name = food.name,
                    price = food.price,
                    image = food.image,
                    quantity = food.quantity,
                    onPlusClick = {
                        cartScreenViewModel.addFoodCart(
                            FoodDataModel(food.name, food.image, food.price, "")
                        )
                    },
                    onMinusClick = { cartScreenViewModel.minusCount(food) },
                    onDeleteClick = { cartScreenViewModel.deleteFood(food) }
                )
            }
        }
    }
}

@Composable
fun CartItem(
    name: String,
    price: String,
    image: Int,
    quantity: Int,
    onPlusClick: () -> Unit,
    onMinusClick: () -> Unit,
    onDeleteClick: () -> Unit
             ) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Картинка еды
        Image(
            painter = painterResource(id = image), // Заглушка пиццы
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFF2B2B3D)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Информация о товаре
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column {
                    Text(
                        text = "$name",
                        maxLines = 2,
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 20.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "$price",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Кнопка удаления (красный крестик)
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(Color(0xFFE74C3C), CircleShape)
                        .clip(CircleShape)
                        .clickable {
                            onDeleteClick()
                                   },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(14.dp)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "14\"",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
                
                // Блок + 1 -
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    // Кнопка минус
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .background(Color(0xFF2B2B3D), CircleShape)
                            .clip(CircleShape)
                            .clickable {
                                onMinusClick()
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .width(10.dp)
                                .height(1.5.dp)
                                .background(Color.Gray)
                        )
                    }
                    
                    Text(
                        text = "$quantity",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    
                    // Кнопка плюс
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .background(Color(0xFF2B2B3D), CircleShape)
                            .clip(CircleShape)
                            .clickable {
                                onPlusClick()
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                }
            }
        }
    }
}
@Composable
fun CustomBottomNavigation(
    onFavoriteClick: () -> Unit,
    onCartClick: () -> Unit,
    onHomeClick: () -> Unit,
    isSelectedHome: Boolean,
    isSelectedSaved: Boolean,
    isSelectedCart: Boolean,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
            .background(Color(0xFF1A1A1A)) // Темно-серый фон бара
            .padding(horizontal = 30.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomIcon(iconRes = R.drawable.home_2, onClick = onHomeClick,isSelectedHome)
            BottomIcon(iconRes = R.drawable.heart, onClick = onFavoriteClick,isSelectedSaved)
            BottomIcon(iconRes = R.drawable.search, onClick = {},)
            BottomIcon(iconRes = R.drawable.ic_notification, onClick = {})
            BottomIcon(iconRes = R.drawable.trolly_25, onClick = onCartClick,isSelectedCart)
        }
    }
}

@Composable
fun BottomIcon(
    iconRes: Int,
    onClick: () -> Unit,
    isSelected: Boolean = false
) {
    val gradient = Brush.horizontalGradient(listOf(GradientStart, GradientEnd))

    Box(
        modifier = Modifier
            .size(50.dp)
            .background(
                if (isSelected) gradient else
                    Brush.linearGradient(listOf(Color.Transparent, Color.Transparent)),
                RoundedCornerShape(15.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        IconButton(onClick = onClick) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                tint = if (isSelected) Color.White else Color.Red,
                modifier = Modifier.size(28.dp)
            )
        }
    }
}
