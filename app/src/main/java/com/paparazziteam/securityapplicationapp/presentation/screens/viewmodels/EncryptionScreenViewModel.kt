package com.paparazziteam.securityapplicationapp.presentation.screens.viewmodels

import androidx.lifecycle.ViewModel
import com.paparazziteam.securityapplicationapp.domain.EncryptedScreenIntent
import com.paparazziteam.securityapplicationapp.framework.encryption.AESHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber
import javax.crypto.SecretKey
import javax.inject.Inject

@HiltViewModel
class EncryptionScreenViewModel @Inject constructor() : ViewModel() {

    private val key:SecretKey = AESHelper.getKetOrCreate()

    private val _intent : MutableStateFlow<EncryptedScreenIntent> = MutableStateFlow(EncryptedScreenIntent())

    val intent = _intent.asStateFlow()

    fun encryptText(text:String) {
        Timber.d("Text to encrypt: $text")
        val encryptedText =  AESHelper.encryptText(text, key)
        Timber.d("Encrypted text: $encryptedText")
    }

    fun decryptText(text:String) {
        Timber.d("Text to decrypt: $text")
        val decryptedText = AESHelper.decryptText(text, key)
        Timber.d("Decrypted text: $decryptedText")
    }
}