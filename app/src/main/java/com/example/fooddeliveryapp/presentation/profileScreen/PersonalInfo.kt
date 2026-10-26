package com.example.fooddeliveryapp.presentation.profileScreen

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.fooddeliveryapp.presentation.profileScreen.viewmodel.ProfileViewModel
import com.example.fooddeliveryapp.ui.theme.DarkBackground
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun PersonalInfo(
    profileViewModel: ProfileViewModel? = null,
    fullName: String = "Vishal Khadok",
    email: String = "hello@halallab.co",
    phone: String = "408-841-0926",
    bio: String = "I love fast food",
    userPhotoUrl: String? = null,
    onBackClick: () -> Unit = {},
    onEditClick: () -> Unit = {}
) {
    val userDataFlow = remember(profileViewModel) {
        profileViewModel?.userData ?: MutableStateFlow(null)
    }
    val userDataState by userDataFlow.collectAsState()

    val currentName = userDataState?.name ?: fullName
    val currentEmail = userDataState?.email ?: email
    val currentPhone = userDataState?.phone ?: phone
    val currentBio = userDataState?.bio ?: bio
    val currentPhoto = userDataState?.photoUrl ?: userPhotoUrl

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            // Header Top Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier
                        .size(45.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF262835))
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }

                Text(
                    text = "Personal Info",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Text(
                    text = "EDIT",
                    color = Color(0xFFFF7622),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(textDecoration = TextDecoration.Underline),
                    modifier = Modifier.clickable { onEditClick() }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // User Avatar and Name
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF262835))
                ) {
                    if (!currentPhoto.isNullOrEmpty()) {
                        AsyncImage(
                            model = currentPhoto,
                            contentDescription = "Profile Picture",
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Profile Icon",
                            tint = Color(0xFFFF7622),
                            modifier = Modifier.size(60.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.width(20.dp))

                Column {
                    Text(
                        text = currentName,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = currentBio,
                        fontSize = 14.sp,
                        color = Color(0xFFA0A5BA)
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Information Card
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                shape = RoundedCornerShape(16.dp),
                color = Color(0xFF262835)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    PersonalInfoItem(
                        icon = Icons.Default.Person,
                        iconTint = Color(0xFFFF7622),
                        label = "FULL NAME",
                        value = currentName
                    )

                    PersonalInfoItem(
                        icon = Icons.Default.Email,
                        iconTint = Color(0xFF5B67CA),
                        label = "EMAIL",
                        value = currentEmail
                    )

                    PersonalInfoItem(
                        icon = Icons.Default.Phone,
                        iconTint = Color(0xFF38B6FF),
                        label = "PHONE NUMBER",
                        value = currentPhone
                    )
                }
            }
        }
    }
}

@Composable
fun PersonalInfoItem(
    icon: ImageVector,
    iconTint: Color,
    label: String,
    value: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color(0xFF333544))
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = iconTint,
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = label,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFFA0A5BA)
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = value,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PersonalInfoPreview() {
    PersonalInfo()
}
