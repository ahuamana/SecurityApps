package com.paparazziteam.securityapplicationapp.presentation.screens.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paparazziteam.securityapplicationapp.domain.EncryptedState
import com.paparazziteam.securityapplicationapp.domain.EncryptionViewIntent
import com.paparazziteam.securityapplicationapp.framework.encryption.AESHelper
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.crypto.SecretKey
import javax.inject.Inject

@HiltViewModel
class EncryptionScreenViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

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


    fun encryptText(text:String) = viewModelScope.launch{
        Timber.d("Text to encrypt: $text")
        val encryptedText =  AESHelper.encryptText(text, key, context)
        Timber.d("Encrypted text: $encryptedText")
        //show on ui.
        val currentState = _intent.value
        _intent.value = currentState.copy(outputTextEncrypt = encryptedText)
    }

    fun decryptText(text:String) {
        Timber.d("Text to decrypt: $text")
        val decryptedText = AESHelper.decryptText(text, key, context)
        Timber.d("Decrypted text: $decryptedText")
        //show on ui.
        val currentState = _intent.value
        _intent.value = currentState.copy(outputTextDecrypt = decryptedText)
    }
}