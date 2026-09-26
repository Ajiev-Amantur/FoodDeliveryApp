package com.example.fooddeliveryapp.presentation.cartScreen

import android.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.util.TableInfo



@Composable
fun CardItem(
    image: Int,
    cardNumber: String,
    cardName: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color.DarkGray)

    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween, // Лево и право
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = cardName, color = Color.White,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.
                    padding(horizontal = 12.dp, vertical = 12.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        contentDescription = "",
                        painter = painterResource(image),
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "**** **** **** *" + cardNumber.takeLast(3), color = Color.White)
                }
            }

            Icon(Icons.Default.ArrowDropDown,
                contentDescription = null)
        }
    }
}