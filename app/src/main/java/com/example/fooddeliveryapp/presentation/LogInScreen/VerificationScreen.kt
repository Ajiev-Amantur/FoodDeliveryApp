package com.example.fooddeliveryapp.presentation.LogInScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.ui.theme.DarkBackground
import com.example.fooddeliveryapp.ui.theme.GradientEnd
import com.example.fooddeliveryapp.ui.theme.GradientStart

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun VerificationScreen(
    userEmail: String = "example@gmail.com",
    onBackClick: () -> Unit = {},
    onVerifyClick: (String) -> Unit = {},
    onResendClick: () -> Unit = {}
) {
    // Храним 4 цифры в списке
    val otpCode = remember { mutableStateListOf("", "", "", "") }

    // Фокус-реквестеры для автоматического перехода на следующий квадратик
    val focusRequesters = remember { List(4) { FocusRequester() } }

    val gradient = Brush.horizontalGradient(listOf(GradientStart, GradientEnd))

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.ic_food_fon),
                contentScale = ContentScale.Crop
            )
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            // 1. ВЕРХНЯЯ ШАПКА
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 24.dp)
            ) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier
                        .size(45.dp)
                        .align(Alignment.Start)
                        .clip(CircleShape)
                        .background(Color.White)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Verification",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "We have sent a code to your email",
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = userEmail,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            // 2. НИЖНЯЯ КАРТОЧКА С ВВОДОМ КОДА
            Surface(
                color = DarkBackground,
                shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp)
                ) {
                    // Заголовок над кодом и кнопка повтора
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "CODE",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )

                        Row {
                            Text(
                                text = "Resend",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                textDecoration = TextDecoration.Underline,
                                modifier = Modifier.clickable { onResendClick() }
                            )
                            Text(
                                text = " in 50sec",
                                fontSize = 12.sp,
                                color = Color.White.copy(alpha = 0.6f)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // 4 КВАДРАТИКА ДЛЯ ВВОДА ЦИФР
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        repeat(4) { index ->
                            OutlinedTextField(
                                value = otpCode[index],
                                onValueChange = { newValue ->
                                    if (newValue.length <= 1) {
                                        otpCode[index] = newValue
                                        // Автопереход на следующий квадратик при вводе
                                        if (newValue.isNotEmpty() && index < 3) {
                                            focusRequesters[index + 1].requestFocus()
                                        }
                                    }
                                },
                                singleLine = true,
                                textStyle = TextStyle(
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center,
                                    color = Color.White
                                ),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                shape = RoundedCornerShape(12.dp),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedTextColor = Color.White,
                                    unfocusedTextColor = Color.White,
                                    unfocusedContainerColor = Color(0xFF262835),
                                    focusedContainerColor = Color(0xFF262835),
                                    unfocusedBorderColor = Color.Transparent,
                                    focusedBorderColor = Color(0xFFFF7622)
                                ),
                                modifier = Modifier
                                    .size(60.dp)
                                    .focusRequester(focusRequesters[index])
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(28.dp))

                    // КНОПКА VERIFY
                    Button(
                        onClick = { onVerifyClick(otpCode.joinToString("")) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(gradient),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        )
                    ) {
                        Text(
                            text = "VERIFY",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}