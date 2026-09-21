package com.example.fooddeliveryapp.presentation.cartScreen

import androidx.compose.animation.animateContentSize
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.domain.model.FoodDataModel
import com.example.fooddeliveryapp.presentation.cartScreen.viewModel.CartScreenViewModel
import com.example.fooddeliveryapp.ui.theme.GradientEnd
import com.example.fooddeliveryapp.ui.theme.GradientStart

import com.example.fooddeliveryapp.presentation.components.CustomBottomNavigation

@Composable
fun CartScreen(
    cartScreenViewModel: CartScreenViewModel,
    onBackClick: () -> Unit,
    onPaymentClick: () -> Unit
) {
    val gradient = Brush.horizontalGradient(listOf(GradientStart, GradientEnd))
    val foods by cartScreenViewModel.cartFood.collectAsState()
    var isSelected by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = Color.Black, // Сделал фон черным
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
            val gradient = Brush.horizontalGradient(listOf(GradientStart, GradientEnd))

            // Основной контейнер, который плавно растет вверх
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize() // Плавная анимация изменения размера
                    .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                    // Если выбрано — фон блока темно-серый (для темной темы), если нет — прозрачный
                    .background(if (isSelected) Color(0xFF1A1A1A) else Color.Transparent)
                    .padding(20.dp)
            ) {
                if (!isSelected) {
                    // СОСТОЯНИЕ 1: Кнопка Checkout с градиентом
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .background(gradient, RoundedCornerShape(20.dp))
                            .clip(RoundedCornerShape(20.dp))
                            .clickable { isSelected = true },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Checkout",
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                } else {
                    // СОСТОЯНИЕ 2: Темный блок (Адрес + Итог + Place Order)
                    TextButton(onClick = {isSelected = false},
                        modifier = Modifier.height(30.dp),
                        contentPadding = PaddingValues(0.dp)
                        ) {
                        Text(
                            text = "CANCEL",
                            color = Color(0xFFFE724C), // Оранжевый, чтобы сочетался с кнопкой EDIT
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "DELIVERY ADDRESS",
                            color = Color.Gray,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "EDIT",
                            color = Color(0xFFFE724C),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.clickable { /* Редактировать */ })
                    }

                    Spacer(modifier = Modifier.height(10.dp))


                    Text(
                        text = "2118 Thornridge Cir. Syracuse",
                        color = Color.White,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF2B2B3D),
                                RoundedCornerShape(10.dp))
                            .padding(15.dp)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.Gray,
                                        fontSize = 14.sp
                                    )
                                ) { append("TOTAL: ") }
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.White,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.ExtraBold
                                    )
                                ) { append("$96") }
                            }
                        )
                        Text("Breakdown >", color = Color(0xFFFE724C), fontSize = 14.sp)
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Кнопка Place Order тоже с градиентом
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .background(
                                gradient,
                                RoundedCornerShape(20.dp)
                            )
                            .clip(RoundedCornerShape(20.dp))
                            .clickable { onPaymentClick()},
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "PLACE ORDER",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        if (foods.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = Color(0xFF00C569),
                    trackColor = Color.Gray.copy(alpha = 0.2f)
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                contentPadding = PaddingValues(bottom = 20.dp)
            ) {
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
            painter = painterResource(id = image),
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
