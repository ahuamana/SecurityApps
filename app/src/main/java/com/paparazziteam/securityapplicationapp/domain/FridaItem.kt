package com.paparazziteam.securityapplicationapp.domain

import com.paparazziteam.securityapplicationapp.presentation.screens.navigation.EncryptionScreen

data class FridaItem(
    val id: Int,
    val name: String,
    val description: String,
    val icon: Int,
    val route: EncryptionScreen
)
