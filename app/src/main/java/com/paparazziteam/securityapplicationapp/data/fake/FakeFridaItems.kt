package com.paparazziteam.securityapplicationapp.data.fake

import com.paparazziteam.securityapplicationapp.R
import com.paparazziteam.securityapplicationapp.domain.FridaItem
import com.paparazziteam.securityapplicationapp.presentation.screens.navigation.EncryptionScreen

object FakeFridaItems {

    fun getFridaItems() = listOf(
        FridaItem(
            id = 1,
            name = "AES",
            description = "AES Encryption",
            icon = R.drawable.ic_key,
            route = EncryptionScreen.AESEncryption
        ),
        /*FridaItem(
            id = 2,
            name = "DES",
            description = "DES Encryption",
            icon = R.drawable.ic_lock
        ),
        FridaItem(
            id = 3,
            name = "RSA",
            description = "RSA Encryption",
            icon = R.drawable.ic_lock
        ),*/
        FridaItem(
            id = 4,
            name = "SHA",
            description = "SHA Encryption",
            icon = R.drawable.ic_lock,
            route = EncryptionScreen.OtherEncryption
        ),
        /*FridaItem(
            id = 5,
            name = "MD5",
            description = "MD5 Encryption",
            icon = R.drawable.ic_lock
        ),*/
        FridaItem(
            id = 6,
            name = "HMAC",
            description = "HMAC Encryption",
            icon = R.drawable.ic_lock,
            route = EncryptionScreen.OtherEncryption
        ),
        /*FridaItem(
            id = 7,
            name = "PBKDF2",
            description = "PBKDF2 Encryption",
            icon = R.drawable.ic_lock
        ),*/
        FridaItem(
            id = 8,
            name = "BCrypt",
            description = "BCrypt Encryption",
            icon = R.drawable.ic_lock,
            route = EncryptionScreen.OtherEncryption
        ),
        FridaItem(
            id = 9,
            name = "SCrypt",
            description = "SCrypt Encryption",
            icon = R.drawable.ic_lock,
            route = EncryptionScreen.OtherEncryption
        ),
        /*FridaItem(
            id = 10,
            name = "Argon2",
            description = "Argon2 Encryption",
            icon = R.drawable.ic_lock
        ),*/
    )
}