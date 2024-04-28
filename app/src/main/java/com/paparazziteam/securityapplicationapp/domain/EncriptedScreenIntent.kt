package com.paparazziteam.securityapplicationapp.domain

data class EncryptedScreenIntent(
    val inputTextEncrypt: String = "",
    val outputTextEncrypt: String = "",
    val inputTextDecrypt: String = "",
    val outputTextDecrypt: String = ""
)