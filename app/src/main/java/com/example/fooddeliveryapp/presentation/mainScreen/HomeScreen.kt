package com.example.fooddeliveryapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fooddeliveryapp.presentation.mainScreen.viewmodel.FoodViewModel
import com.example.fooddeliveryapp.ui.theme.GradientEnd
import com.example.fooddeliveryapp.ui.theme.GradientStart
import com.example.fooddeliveryapp.ui.theme.MainPink

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenUI(foodViewModel: FoodViewModel) {
    val foodList by foodViewModel.food.collectAsState()

    Scaffold(
        bottomBar = {
            CustomBottomNavigation()
        },
        containerColor = Color.Black
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.Black)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(horizontal = 4.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                // Header
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.menu),
                        contentDescription = "",
                        modifier = Modifier.size(30.dp)
                    )
                    Image(
                        painter = painterResource(R.drawable.ic_launcher_foreground),
                        contentDescription = "",
                        modifier = Modifier.size(30.dp)
                    )
                }

                Text(
                    text = buildAnnotatedString {
                        append("Hi, Aman\nLet's Make Your ")
                        withStyle(style = SpanStyle(Color.Red)) {
                            append("Breakfast \n ")
                        }
                        append("Amazing")
                    },
                    fontSize = 30.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                )

                var searchText by remember { mutableStateOf("") }
                var isActive by remember { mutableStateOf(false) }

                // Search Bar Row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    SearchBar(
                        query = searchText,
                        onQueryChange = { searchText = it },
                        placeholder = { Text("Burger, Pizza, Cake...", color = Color.Gray) },
                        onSearch = { isActive = false },
                        active = false,
                        onActiveChange = { isActive = it },
                        leadingIcon = {
                            Image(
                                painter = painterResource(R.drawable.search),
                                contentDescription = "",
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        colors = SearchBarDefaults.colors(containerColor = Color.Transparent),
                        modifier = Modifier
                            .weight(1f)
                            .height(54.dp)
                            .border(1.dp, Color(0xFFFA00FF), RoundedCornerShape(30.dp))
                    ) {}

                    Spacer(modifier = Modifier.width(12.dp))

                    Box(
                        modifier = Modifier
                            .size(54.dp)
                            .background(Color.Black, RoundedCornerShape(20.dp))
                            .border(1.dp, Color(0xFFFA00FF), RoundedCornerShape(20.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.setting_5),
                            contentDescription = "",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }

                Text(
                    "Categories",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 15.dp)
                ) {
                    item { CategoryItem(null, "All", isSelected = true) }
                    item { CategoryItem(R.drawable.hamburger, "Burger") }
                    item { CategoryItem(R.drawable.pizza, "Pizza") }
                }

                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Popular now", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text("See all..", color = Color.Gray, fontSize = 14.sp)
                }

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 15.dp, vertical = 10.dp)
                ) {
                    items(foodList) { foodItem ->
                        FoodCard(
                            name = foodItem.name,
                            price = foodItem.price,
                            imageRes = foodItem.image,
                            description = foodItem.description
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Composable
fun CustomBottomNavigation() {
    val gradient = Brush.horizontalGradient(listOf(GradientStart, GradientEnd))
    
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
            // Home с градиентным фоном
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(gradient, RoundedCornerShape(15.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.home_2),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )
            }

            // Остальные иконки
            BottomIcon(R.drawable.heart)
            BottomIcon(R.drawable.search)
            BottomIcon(R.drawable.notification_76)
            BottomIcon(R.drawable.trolly_25)
        }
    }
}

@Composable
fun BottomIcon(iconRes: Int) {
    IconButton(onClick = { }) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            tint = Color.Red,
            modifier = Modifier.size(28.dp)
        )
    }
}
