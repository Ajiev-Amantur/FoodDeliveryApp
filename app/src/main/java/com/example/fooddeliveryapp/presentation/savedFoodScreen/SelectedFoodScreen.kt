package com.example.fooddeliveryapp.presentation.savedFoodScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import com.example.fooddeliveryapp.presentation.savedFoodScreen.viewmodel.SavedFoodViewModel

@Composable
fun SelectedFoodScreen(savedFoodViewModel: SavedFoodViewModel) {
    val favoriteFoodList by savedFoodViewModel.favoriteFood.collectAsState()
    
    Scaffold(containerColor = Color.Black) { innerPadding ->
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
                        }
                    )
                }
            }
        }
    }
}
