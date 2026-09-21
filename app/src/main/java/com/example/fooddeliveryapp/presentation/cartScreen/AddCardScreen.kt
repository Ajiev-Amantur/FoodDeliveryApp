package com.example.fooddeliveryapp.presentation.cartScreen


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fooddeliveryapp.presentation.cartScreen.viewModel.CartScreenViewModel
import com.example.fooddeliveryapp.ui.theme.GradientEnd
import com.example.fooddeliveryapp.ui.theme.GradientStart

@Composable
fun AddCardScreen(
    onBackClick: () -> Unit,
    cartScreenViewModel: CartScreenViewModel,
    onAddClick: () -> Unit
) {
    val gradient = Brush.horizontalGradient(listOf(GradientStart, GradientEnd))
    var textName by cartScreenViewModel.textNameHolder
    var textCardNumber by cartScreenViewModel.cardNumber
    var textExpireDate by cartScreenViewModel.expireDateCard
    var textCVC by cartScreenViewModel.CVC
    Scaffold(
        containerColor = Color.Black,
        bottomBar = {
            Button(
                onClick = { onAddClick() },
                modifier = Modifier.fillMaxWidth()
                    .padding(20.dp)
                .height(60.dp)
                    .background(gradient,
                        RoundedCornerShape(12.dp)),
                        colors = (ButtonDefaults.buttonColors
                        (containerColor = Color.Transparent))
                ) {
                Text("ADD & MAKE PAYMENT",
                    fontSize = 20.sp,
                    color = Color.White)
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 10.dp)
                .statusBarsPadding()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { onBackClick() },
                    modifier = Modifier.size(45.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0xFF1A1A1A),
                            RoundedCornerShape(20.dp))
                        .border(1.dp, Color.Gray,RoundedCornerShape(20.dp))
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "",
                        tint = Color.White,
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                "Card Holder Name",
                fontSize = 12.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = textName,
                onValueChange = { textName = it },
                placeholder = { Text("Ajiev Amantur")},
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                "Card Number",
                fontSize = 12.sp,
                color = Color.White,
                modifier = Modifier.padding(top = 6.dp)
            )
            OutlinedTextField(
                value = textCardNumber,
                onValueChange = { textCardNumber = it },
                placeholder = { Text("---- ---- ---- ----") },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        "EXPIRE DATE",
                        fontSize = 12.sp,
                        color = Color.White
                    )
                    OutlinedTextField(
                        value = textExpireDate,
                        onValueChange = { textExpireDate = it },
                        placeholder = { Text("mm/yyyy") },
                        shape = RoundedCornerShape(12.dp),
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))

                Column(modifier = Modifier.weight(1f)) {

                    Text(
                        "CVC",
                        fontSize = 12.sp,
                        color = Color.White
                    )
                    OutlinedTextField(
                        value = textCVC,
                        onValueChange = { textCVC = it },
                        placeholder = { Text("***") },
                        shape = RoundedCornerShape(12.dp)
                    )
                }
            }
        }

    }
}