package com.example.fooddeliveryapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FoodCard(
    name: String,
    price: String,
    imageRes: Int,
    description: String,
    isFavorite: Boolean = false, 
    onClick: () -> Unit,
    onCardClick: () -> Unit = {} // Новый параметр для клика на саму карточку
) {
    Box(
        modifier = Modifier
            .width(180.dp)
            .padding(8.dp)
            .border(1.dp, Color.Red.copy(alpha = 0.7f), RoundedCornerShape(30.dp))
            .background(Color(0xFF1A1A1A), RoundedCornerShape(30.dp))
            .clickable { onCardClick() } // Делаем всю карточку кликабельной!
            .padding(16.dp)
    ) {
        // Кнопка сердечко в углу
        Icon(
            painter = painterResource(id = R.drawable.heart),
            contentDescription = null,
            // Если в избранном - желтое, иначе белое (или серое)
            tint = if (isFavorite) Color.Yellow else Color.Gray, 
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(24.dp)
                .clickable { onClick() }
        )

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = name, color = Color.White
                , fontWeight = FontWeight.Bold
                , fontSize = 18.sp,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 22.sp,
                maxLines = 2)
            Text(
                text = description,
                color = Color.Gray,
                fontSize = 10.sp,
                maxLines = 1,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.price),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = price,
                    color = Color.Yellow,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp
                )
            }
        }
    }
}
