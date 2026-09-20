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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fooddeliveryapp.FoodCard
import com.example.fooddeliveryapp.domain.model.FoodDataModel
import com.example.fooddeliveryapp.presentation.savedFoodScreen.viewmodel.SavedFoodViewModel
import com.example.fooddeliveryapp.presentation.components.CustomBottomNavigation

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
        if (favoriteFoodList.isEmpty()){
            Box(modifier = Modifier.fillMaxSize()
                .padding(innerPadding),
                contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    color = Color(0xFF00C569),
                    trackColor = Color.Gray.copy(0.2f)
                )
            }
        }else{
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = innerPadding.calculateBottomPadding())
                .statusBarsPadding()
        ) {
            IconButton(
                onClick = { onBackClick() },
                modifier = Modifier
                    .padding(start = 20.dp, top = 8.dp)
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
}}
