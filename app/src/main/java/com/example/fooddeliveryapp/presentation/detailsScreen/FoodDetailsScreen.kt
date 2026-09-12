package com.example.fooddeliveryapp.presentation.detailsScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.domain.model.FoodDataModel
import com.example.fooddeliveryapp.ui.theme.GradientEnd
import com.example.fooddeliveryapp.ui.theme.GradientStart
@Composable
fun FoodDetailsScreen(
    foodDataModel: FoodDataModel,
    onBackClick: () -> Unit,
    onFavoriteClick: () -> Unit,
) {
    val gradient = Brush.horizontalGradient(listOf(GradientStart, GradientEnd))

    Scaffold(
        containerColor = Color.Black,
        bottomBar = {
            // Главная кнопка внизу
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .height(60.dp)
                    .background(gradient, RoundedCornerShape(20.dp))
                    .clip(RoundedCornerShape(20.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Add to Cart",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
        ) {
            // Верхняя панель (Назад и Сердечко)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick ={ onBackClick()},
                    modifier = Modifier
                        .size(45.dp)
                        .background(Color(0xFF1A1A1A), RoundedCornerShape(12.dp))
                        .border(1.dp, Color.Gray.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = Color.White
                    )
                }

                IconButton(
                    onClick = { /* В избранное */ },
                    modifier = Modifier
                        .size(45.dp)
                        .background(Color(0xFF1A1A1A), RoundedCornerShape(12.dp))
                        .border(1.dp, Color.Gray.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.heart),
                        contentDescription = null,
                        tint = if (foodDataModel.isFavorite) Color.Yellow else Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            // Центр: Изображение с неоновым свечением
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp),
                contentAlignment = Alignment.Center
            ) {
                // Мягкое красное свечение сзади
                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .background(Color.Red.copy(alpha = 0.15f), CircleShape)
                        .border(2.dp, Color.Red.copy(alpha = 0.2f), CircleShape)
                )
                
                Image(
                    painter = painterResource(id = R.drawable.ic_hamburger), // Временная картинка
                    contentDescription = null,
                    modifier = Modifier.size(230.dp),
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Название и Цена
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${foodDataModel.name}",
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                
                Text(
                    text = "${foodDataModel.price}",
                    color = Color.Yellow,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Раздел описания
            Text(
                text = "${foodDataModel.description}",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            
            Spacer(modifier = Modifier.height(10.dp))
            
            Text(
                text = "Enjoy our signature beef burger with extra cheese, fresh lettuce, and our secret house sauce. Served on a toasted brioche bun.",
                color = Color.Gray,
                fontSize = 16.sp,
                lineHeight = 24.sp
            )

            Spacer(modifier = Modifier.height(30.dp))
            
            // Фишки (Бейджи)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                InfoBadge(text = "25-30 min", icon = R.drawable.ic_launcher_foreground)
                InfoBadge(text = "Free Delivery", icon = R.drawable.trolly_25)
            }
        }
    }
}
@Composable
fun InfoBadge(text: String, icon: Int) {
    Row(
        modifier = Modifier
            .background(Color(0xFF1A1A1A), RoundedCornerShape(12.dp))
            .padding(horizontal = 14.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color.Red,
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, color = Color.White, fontSize = 13.sp)
    }
}
