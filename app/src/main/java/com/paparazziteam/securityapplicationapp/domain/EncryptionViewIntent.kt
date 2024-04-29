package com.paparazziteam.securityapplicationapp.domain

sealed class EncryptionViewIntent {
    data class TextChangedEncrypt(val text: String) : EncryptionViewIntent()
    data class TextChangedDecrypt(val text: String) : EncryptionViewIntent()
}