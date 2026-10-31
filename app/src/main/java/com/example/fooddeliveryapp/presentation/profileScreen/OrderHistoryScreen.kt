package com.example.fooddeliveryapp.presentation.profileScreen

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fooddeliveryapp.domain.model.OrderHistoryModel
import com.example.fooddeliveryapp.presentation.profileScreen.viewmodel.OrderHistoryViewModel
import com.example.fooddeliveryapp.ui.theme.DarkBackground
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun OrderHistoryScreen(
    orderHistoryViewModel: OrderHistoryViewModel? = null,
    onBackClick: () -> Unit = {},
    onReorderClick: (OrderHistoryModel) -> Unit = {}
) {
    var selectedFilter by remember { mutableStateOf("All") }
    val filters = listOf("All", "Completed", "Processing")

    val historyFlow = remember(orderHistoryViewModel) {
        orderHistoryViewModel?.foodHistory ?: MutableStateFlow(emptyList())
    }
    val realOrders by historyFlow.collectAsState()

    val fallbackOrders = remember {
        listOf(
            OrderHistoryModel(
                orderId = "#FD-4921",
                date = "28 Oct, 14:30",
                status = "Delivered",
                itemsSummary = "2x Burger Combo, 1x Coca-Cola",
                itemCount = 3,
                totalPrice = "$24.50"
            ),
            OrderHistoryModel(
                orderId = "#FD-3810",
                date = "25 Oct, 19:15",
                status = "Delivered",
                itemsSummary = "1x Pepperoni Pizza 30cm, 2x Garlic Sauce",
                itemCount = 3,
                totalPrice = "$18.90"
            ),
            OrderHistoryModel(
                orderId = "#FD-2104",
                date = "20 Oct, 12:00",
                status = "Processing",
                itemsSummary = "1x Chicken Wrap, 1x French Fries",
                itemCount = 2,
                totalPrice = "$12.00"
            )
        )
    }

    val orders = if (realOrders.isNotEmpty()) realOrders else fallbackOrders

    val filteredOrders = when (selectedFilter) {
        "Completed" -> orders.filter { it.status.equals("Delivered", ignoreCase = true) }
        "Processing" -> orders.filter { it.status.equals("Processing", ignoreCase = true) }
        else -> orders
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // Top Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier
                        .size(45.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF262835))
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "Order History",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Filter Chips
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                filters.forEach { filter ->
                    val isSelected = selectedFilter == filter
                    Surface(
                        onClick = { selectedFilter = filter },
                        shape = CircleShape,
                        color = if (isSelected) Color(0xFFFF7622) else Color(0xFF262835),
                        modifier = Modifier.height(38.dp)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.padding(horizontal = 18.dp)
                        ) {
                            Text(
                                text = filter,
                                fontSize = 13.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                color = Color.White
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Orders List
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                items(filteredOrders) { order ->
                    OrderHistoryCard(
                        order = order,
                        onReorderClick = { onReorderClick(order) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun OrderHistoryCard(
    order: OrderHistoryModel,
    onReorderClick: () -> Unit = {}
) {
    val isDelivered = order.status.equals("Delivered", ignoreCase = true)
    val statusBg = if (isDelivered) Color(0xFF2ECC71).copy(alpha = 0.15f) else Color(0xFFFF7622).copy(alpha = 0.15f)
    val statusText = if (isDelivered) Color(0xFF2ECC71) else Color(0xFFFF7622)

    Surface(
        color = Color(0xFF262835),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Header: Date & Status Badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = order.orderId,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = order.date,
                        fontSize = 12.sp,
                        color = Color(0xFFA0A5BA)
                    )
                }

                Surface(
                    color = statusBg,
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text(
                        text = order.status,
                        color = statusText,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Middle: Icon + Details
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFF333544)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = null,
                        tint = Color(0xFFFF7622),
                        modifier = Modifier.size(22.dp)
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = order.itemsSummary,
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "${order.totalPrice} • ${order.itemCount} items",
                        color = Color(0xFFFF7622),
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Reorder Button
            Button(
                onClick = onReorderClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF7622)
                )
            ) {
                Text(
                    text = "REORDER",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OrderHistoryScreenPreview() {
    OrderHistoryScreen()
}
