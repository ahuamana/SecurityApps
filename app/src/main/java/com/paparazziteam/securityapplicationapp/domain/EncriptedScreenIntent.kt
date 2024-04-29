package com.paparazziteam.securityapplicationapp.domain

data class EncryptedState(
    val inputTextEncrypt: String = "",
    val outputTextEncrypt: String = "",
    val inputTextDecrypt: String = "",
    val outputTextDecrypt: String = ""
)