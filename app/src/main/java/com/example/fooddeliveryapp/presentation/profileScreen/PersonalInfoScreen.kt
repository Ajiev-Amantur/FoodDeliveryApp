package com.example.fooddeliveryapp.presentation.profileScreen

import androidx.compose.runtime.Composable

@Composable
fun PersonalInfoScreen(
    fullName: String = "Vishal Khadok",
    email: String = "hello@halallab.co",
    phone: String = "408-841-0926",
    bio: String = "I love fast food",
    userPhotoUrl: String? = null,
    onBackClick: () -> Unit = {},
    onEditClick: () -> Unit = {}
) {
    PersonalInfo(
        fullName = fullName,
        email = email,
        phone = phone,
        bio = bio,
        userPhotoUrl = userPhotoUrl,
        onBackClick = onBackClick,
        onEditClick = onEditClick
    )
}
