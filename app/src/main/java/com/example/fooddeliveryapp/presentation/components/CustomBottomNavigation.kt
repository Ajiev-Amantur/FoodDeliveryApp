package com.example.fooddeliveryapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.ui.theme.GradientEnd
import com.example.fooddeliveryapp.ui.theme.GradientStart

@Composable
fun CustomBottomNavigation(
    onHomeClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {},
    onCartClick: () -> Unit = {},
    isSelectedHome: Boolean = false,
    isSelectedSaved: Boolean = false,
    isSelectedCart: Boolean = false,
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
            BottomIcon(
                iconRes = R.drawable.home_2,
                onClick = onHomeClick,
                isSelected = isSelectedHome
            )
            BottomIcon(
                iconRes = R.drawable.heart,
                onClick = onFavoriteClick,
                isSelected = isSelectedSaved
            )
            BottomIcon(
                iconRes = R.drawable.search,
                onClick = {}
            )
            BottomIcon(
                iconRes = R.drawable.ic_notification,
                onClick = {}
            )
            BottomIcon(
                iconRes = R.drawable.trolly_25,
                onClick = onCartClick,
                isSelected = isSelectedCart
            )
        }
    }
}

@Composable
fun BottomIcon(
    iconRes: Int,
    onClick: () -> Unit = {},
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
                tint = if (isSelected) Color.White else Color.Gray, // Изменили Red на Gray для неактивных иконок
                modifier = Modifier.size(28.dp)
            )
        }
    }
}
