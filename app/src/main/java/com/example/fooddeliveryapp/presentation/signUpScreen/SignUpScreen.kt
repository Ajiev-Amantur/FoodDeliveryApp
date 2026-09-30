package com.example.fooddeliveryapp.presentation.signUpScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.ui.theme.DarkBackground
import com.example.fooddeliveryapp.ui.theme.GradientEnd
import com.example.fooddeliveryapp.ui.theme.GradientStart

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SignUpScreen(
    onBackClick: () -> Unit = {},
    onSignUpClick: () -> Unit = {}
) {
    // Состояния для введенных данных
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var reTypePassword by remember { mutableStateOf("") }

    var isPasswordVisible by remember { mutableStateOf(false) }
    var isReTypePasswordVisible by remember { mutableStateOf(false) }

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
                        .align(Alignment.Start)
                        .size(45.dp)
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
                    text = "Sign Up",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Please sign up to get started",
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )
            }

            // 2. НИЖНЯЯ ТЕМНАЯ КАРТОЧКА С ФОРМОЙ
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
                        .verticalScroll(rememberScrollState())
                ) {
                    // FIELD 1: NAME
                    Text(
                        text = "NAME",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    CustomInputField(
                        value = name,
                        onValueChange = { name = it },
                        placeholder = "John doe"
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // FIELD 2: EMAIL
                    Text(
                        text = "EMAIL",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    CustomInputField(
                        value = email,
                        onValueChange = { email = it },
                        placeholder = "example@gmail.com"
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // FIELD 3: PASSWORD
                    Text(
                        text = "PASSWORD",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    CustomInputField(
                        value = password,
                        onValueChange = { password = it },
                        placeholder = "••••••••••••",
                        isPassword = true,
                        isPasswordVisible = isPasswordVisible,
                        onVisibilityToggle = { isPasswordVisible = !isPasswordVisible }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // FIELD 4: RE-TYPE PASSWORD
                    Text(
                        text = "RE-TYPE PASSWORD",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    CustomInputField(
                        value = reTypePassword,
                        onValueChange = { reTypePassword = it },
                        placeholder = "••••••••••••",
                        isPassword = true,
                        isPasswordVisible = isReTypePasswordVisible,
                        onVisibilityToggle = { isReTypePasswordVisible = !isReTypePasswordVisible }
                    )

                    Spacer(modifier = Modifier.height(28.dp))

                    // SIGN UP BUTTON
                    Button(
                        onClick = onSignUpClick,
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
                            text = "SIGN UP",
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

@Composable
fun CustomInputField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isPassword: Boolean = false,
    isPasswordVisible: Boolean = false,
    onVisibilityToggle: () -> Unit = {}
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(text = placeholder, color = Color(0xFFA0A5BA), fontSize = 14.sp)
        },
        singleLine = true,
        visualTransformation = if (isPassword && !isPasswordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
            if (isPassword) {
                IconButton(onClick = onVisibilityToggle) {
                    Text(
                        text = if (isPasswordVisible) "👁" else "👁‍🗨",
                        fontSize = 16.sp
                    )
                }
            }
        },
        shape = RoundedCornerShape(10.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            unfocusedContainerColor = Color(0xFF262835),
            focusedContainerColor = Color(0xFF262835),
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = Color(0xFFFF7622)
        ),
        modifier = Modifier.fillMaxWidth()
    )
}