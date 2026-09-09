package com.example.fooddeliveryapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CategoryItem(
    image: Int?,
    title: String,
    isSelected: Boolean = false,
    onClick: () -> Unit // Добавили параметр
) {
    val backgroundColor =
        if (isSelected) Brush.horizontalGradient(listOf(Color(0xFFFA00FF), Color(0xFFFF0000)))
        else SolidColor(Color(0xFF1A1A1A))
    
    val borderColor = if (isSelected) Color.Transparent else Color.Red.copy(alpha = 0.5f)
    
    Row(
        modifier = Modifier
            .padding(end = 12.dp)
            .clip(RoundedCornerShape(24.dp)) // Чтобы волна клика была круглой
            .clickable { onClick() } // Вешаем клик
            .border(1.dp, color = borderColor, RoundedCornerShape(24.dp))
            .background(backgroundColor, RoundedCornerShape(24.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (image != null) {
            Image(
                painter = painterResource(id = image),
                contentDescription = "",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        Text(
            text = title,
            fontSize = 18.sp,
            color = Color.White
        )
    }
}
