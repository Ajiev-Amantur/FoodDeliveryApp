package com.example.fooddeliveryapp.presentation.cartScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.ui.theme.GradientEnd
import com.example.fooddeliveryapp.ui.theme.GradientStart

@Composable
fun SucsessAddedCard(
    onClick: () -> Unit
){
    val gradient = Brush.horizontalGradient(listOf(GradientStart, GradientEnd))
    Scaffold(
        containerColor = Color.Black,
        bottomBar = {
            Button(
                onClick = {
                    onClick()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                modifier = Modifier.fillMaxWidth()
                    .padding(20.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(gradient),

            ) {

                Text(
                    "Go to Back",
                    fontSize = 30.sp,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    ) {innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize().padding(innerPadding)
                .background(Color.Black),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Text(
                    "Your Card Added!",
                    color = Color.White,
                    fontStyle = FontStyle.Italic,
                    fontSize = 30.sp
                )

                Image(
                    painter = painterResource(R.drawable.ic_sucsess),
                    contentDescription = "", modifier = Modifier.size(200.dp)
                )
                }


            }

        }
    }