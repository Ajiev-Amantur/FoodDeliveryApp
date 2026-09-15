package com.example.fooddeliveryapp.presentation.savedFoodScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fooddeliveryapp.FoodCard
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.domain.model.FoodDataModel
import com.example.fooddeliveryapp.presentation.savedFoodScreen.viewmodel.SavedFoodViewModel
import com.example.fooddeliveryapp.ui.theme.GradientEnd
import com.example.fooddeliveryapp.ui.theme.GradientStart

@Composable
fun SelectedFoodScreen(
    isSelected: Boolean,
    savedFoodViewModel: SavedFoodViewModel,
    onFoodClick: (FoodDataModel) -> Unit,
    onFavoriteClick: () -> Unit,
    onCartClick: () -> Unit
) {
    val favoriteFoodList by savedFoodViewModel.favoriteFood.collectAsState()
    
    Scaffold(
        bottomBar = {
            CustomBottomNavigation(
                onFavoriteClick = {onFavoriteClick},
                onCartClick = { onCartClick},
                isSelected = isSelected
            )
        },
        containerColor = Color.Black)
    { innerPadding ->
        if (favoriteFoodList.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "No saved items yet", color = Color.Gray, fontSize = 18.sp)
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(favoriteFoodList) { foodItem ->
                    FoodCard(
                        name = foodItem.name,
                        price = foodItem.price,
                        imageRes = foodItem.image,
                        description = foodItem.description,
                        isFavorite = foodItem.isFavorite,
                        onClick = {
                            savedFoodViewModel.toggleFavorite(foodItem)
                        },
                        onCardClick = {
                            onFoodClick(foodItem)
                        }
                    )
                }
            }
        }
    }
}
@Composable
fun CustomBottomNavigation(
    onFavoriteClick: () -> Unit,
    onCartClick: () -> Unit,
    isSelected: Boolean,
    onHomeClick: () -> Unit
) {
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
                    .background(if (isSelected) gradient else
                        Brush.linearGradient(listOf(Color.Transparent,Color.Transparent))
                        , RoundedCornerShape(15.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.clickable{
                        onHomeClick()
                    },
                    painter = painterResource(id = com.example.fooddeliveryapp.R.drawable.home_2),
                    contentDescription = null,
                    tint = if (isSelected) Color.White else Color.Transparent,
                    modifier = Modifier.size(30.dp),
                )
            }

            // Остальные иконки
            BottomIcon(iconRes = com.example.fooddeliveryapp.R.drawable.heart, onClick = onFavoriteClick)
            BottomIcon(iconRes = com.example.fooddeliveryapp.R.drawable.search)
            BottomIcon(iconRes = com.example.fooddeliveryapp.R.drawable.notification_76)
            BottomIcon(iconRes = R.drawable.trolly_25, onClick = onCartClick)
        }
    }
}

@Composable
fun BottomIcon(iconRes: Int, onClick: () -> Unit = {}) {
    IconButton(onClick = onClick) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            tint = Color.Red,
            modifier = Modifier.size(28.dp)
        )
    }
}
