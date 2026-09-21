package com.example.fooddeliveryapp.presentation.cartScreen


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.ui.theme.GradientEnd
import com.example.fooddeliveryapp.ui.theme.GradientStart

@Composable
fun PaymentScreen(
    onBackClick: () -> Unit,
    onAddCardClick: () -> Unit,
    onConfirmClick: () -> Unit
) {
    var selectedMethod by remember { mutableStateOf("Mastercard") }
    val gradient = Brush.horizontalGradient(listOf(GradientStart, GradientEnd))

    val paymentMethods = listOf(
        Pair("Cash", R.drawable.ic_cash),
        Pair("Visa", R.drawable.ic_visa),
        Pair("Mastercard", R.drawable.ic_mastercard),
        Pair("PayPal", R.drawable.ic_paypal)
    )

    Scaffold(
        containerColor = Color.Black,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier
                        .size(45.dp)
                        .background(Color(0xFF1A1A1A), RoundedCornerShape(12.dp))
                        .border(1.dp, Color.Gray.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
                Text(
                    text = "Payment",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(start = 20.dp)
                )
            }
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("TOTAL:", color = Color.Gray, fontSize = 14.sp)
                    Text("$96", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(20.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .background(gradient, RoundedCornerShape(20.dp))
                        .clip(RoundedCornerShape(20.dp))
                        .clickable { onConfirmClick() },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "PAY & CONFIRM",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            
            // Список способов оплаты
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(paymentMethods) { method ->
                    PaymentCardItem(
                        image = method.second,
                        title = method.first,
                        isSelected = selectedMethod == method.first,
                        onClick = { selectedMethod = method.first }
                    )
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            // Визуализация карты (как на скриншоте)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color(0xFF1A1A1A), RoundedCornerShape(20.dp))
                    .border(1.dp, Color.Gray.copy(alpha = 0.1f), RoundedCornerShape(20.dp)),
                contentAlignment = Alignment.Center
            ) {
                if (selectedMethod == "Mastercard" || selectedMethod == "Visa") {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        // Здесь можно нарисовать саму карту
                        Box(
                            modifier = Modifier
                                .size(120.dp, 80.dp)
                                .background(
                                    Brush.linearGradient(listOf(Color(0xFFFE724C), Color(0xFFFFC529))),
                                    RoundedCornerShape(10.dp)
                                )
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = "No card added",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "You can add a card and save it for later",
                            color = Color.Gray,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(horizontal = 40.dp)
                        )
                    }
                } else if (selectedMethod == "Cash") {
                    Text("Pay with cash on delivery", color = Color.White)
                } else {
                    Text("Connect your PayPal account", color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            // Кнопка добавления новой карты
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onAddCardClick() },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = Color(0xFFFE724C),
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "ADD NEW",
                    color = Color(0xFFFE724C),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
        }
    }
}
