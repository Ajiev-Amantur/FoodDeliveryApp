package com.example.fooddeliveryapp.presentation.profileScreen

import androidx.compose.runtime.Composable
import com.example.fooddeliveryapp.presentation.profileScreen.viewmodel.ProfileViewModel

@Composable
fun PersonalInfoScreen(
    profileViewModel: ProfileViewModel? = null,
    fullName: String = "Vishal Khadok",
    email: String = "hello@halallab.co",
    phone: String = "408-841-0926",
    bio: String = "I love fast food",
    userPhotoUrl: String? = null,
    onBackClick: () -> Unit = {},
    onEditClick: () -> Unit = {}
) {
    PersonalInfo(
        profileViewModel = profileViewModel,
        fullName = fullName,
        email = email,
        phone = phone,
        bio = bio,
        userPhotoUrl = userPhotoUrl,
        onBackClick = onBackClick,
        onEditClick = onEditClick
    )
}
