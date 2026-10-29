package com.example.fooddeliveryapp.presentation.profileScreen

import androidx.compose.runtime.Composable
import com.example.fooddeliveryapp.presentation.profileScreen.viewmodel.ProfileViewModel

@Composable
fun PersonalInfoScreen(
    profileViewModel: ProfileViewModel? = null,
    onBackClick: () -> Unit = {},
    onEditClick: () -> Unit = {}
) {
    PersonalInfo(
        profileViewModel = profileViewModel,
        onBackClick = onBackClick,
        onEditClick = onEditClick
    )
}
