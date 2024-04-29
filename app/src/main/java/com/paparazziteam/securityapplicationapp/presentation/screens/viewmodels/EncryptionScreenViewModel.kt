package com.paparazziteam.securityapplicationapp.presentation.screens.viewmodels

import androidx.lifecycle.ViewModel
import com.paparazziteam.securityapplicationapp.domain.EncryptedState
import com.paparazziteam.securityapplicationapp.domain.EncryptionViewIntent
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
    private val _intent : MutableStateFlow<EncryptedState> = MutableStateFlow(EncryptedState())

    val intent = _intent.asStateFlow()

    fun processIntent(intent: EncryptionViewIntent) {
        when(intent) {
            is EncryptionViewIntent.TextChangedEncrypt -> {
                val currentState = _intent.value
                Timber.d("Text to encrypt: ${intent.text}")
                _intent.value = currentState.copy(inputTextEncrypt = intent.text)
            }
            is EncryptionViewIntent.TextChangedDecrypt -> {
                val currentState = _intent.value
                Timber.d("Text to decrypt: ${intent.text}")
                _intent.value = currentState.copy(inputTextDecrypt = intent.text)
            }
            /*else -> {
                Timber.d("Intent not recognized")
            }*/
        }
    }


    fun encryptText(text:String) {
        Timber.d("Text to encrypt: $text")
        val encryptedText =  AESHelper.encryptText(text, key)
        Timber.d("Encrypted text: $encryptedText")
        //show on ui.
        val currentState = _intent.value
        _intent.value = currentState.copy(outputTextEncrypt = encryptedText)
    }

    fun decryptText(text:String) {
        Timber.d("Text to decrypt: $text")
        val decryptedText = AESHelper.decryptText(text, key)
        Timber.d("Decrypted text: $decryptedText")
        //show on ui.
        val currentState = _intent.value
        _intent.value = currentState.copy(outputTextDecrypt = decryptedText)
    }
}