package com.example.fooddeliveryapp.presentation.savedFoodScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
fun SavedFoodScreen(
    isSelectedHome: Boolean,
    isSelectedSaved: Boolean,
    isSelectedCart: Boolean,
    savedFoodViewModel: SavedFoodViewModel,
    onFoodClick: (FoodDataModel) -> Unit,
    onFavoriteClick: () -> Unit,
    onCartClick: () -> Unit,
    onHomeClick: () -> Unit,
    onBackClick: () -> Unit
) {
    val favoriteFoodList by savedFoodViewModel.favoriteFood.collectAsState()

    Scaffold(
        bottomBar = {
            CustomBottomNavigation(
                onFavoriteClick = { onFavoriteClick() },
                onCartClick = { onCartClick() },
                isSelectedHome = isSelectedHome,
                onHomeClick = { onHomeClick() },
                isSelectedSaved = isSelectedSaved,
                isSelectedCart = isSelectedCart
            )
        },
        containerColor = Color.Black
    )
    { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = innerPadding.calculateBottomPadding())
                .statusBarsPadding()
        ) {
            IconButton(
                onClick = { onBackClick() },
                modifier = Modifier
                    .padding(start = 16.dp, top = 8.dp)
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
            if (favoriteFoodList.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "No saved items yet", color = Color.Gray, fontSize = 18.sp)
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.weight(1f).fillMaxWidth(),
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
                            },
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
                // Остальные иконки
                BottomIcon(iconRes = R.drawable.home_2, onClick = onHomeClick, isSelected = isSelectedHome)
                BottomIcon(
                    iconRes = R.drawable.heart,
                    onClick = onFavoriteClick,
                    isSelected = isSelectedSaved
                )
                BottomIcon(iconRes = R.drawable.search, onClick = { })
                BottomIcon(iconRes = R.drawable.ic_notification, onClick = {})
                BottomIcon(iconRes = R.drawable.trolly_25, onClick = onCartClick, isSelected = isSelectedCart)
            }
        }
    }

    @Composable
    fun BottomIcon(
        iconRes: Int, onClick: () -> Unit = {},
        isSelected: Boolean = false
    ) {
        val gradient = Brush.horizontalGradient(listOf(GradientStart, GradientEnd))
        Box(
            modifier = Modifier.size(50.dp)
                .background(
                    if (isSelected) gradient else
                        Brush.linearGradient(colors = listOf(Color.Transparent, Color.Transparent)),
                    RoundedCornerShape(16.dp)

                ),
            contentAlignment = Alignment.Center,
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
