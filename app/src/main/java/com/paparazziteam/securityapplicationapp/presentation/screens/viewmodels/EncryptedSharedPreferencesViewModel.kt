package com.paparazziteam.securityapplicationapp.presentation.screens.viewmodels

import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.paparazziteam.securityapplicationapp.domain.EncryptedState
import com.paparazziteam.securityapplicationapp.domain.EncryptionViewIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class EncryptedSharedPreferencesViewModel @Inject constructor(
   @ApplicationContext private val context: Context
) : ViewModel() {

    private val masterKeyAlias = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val encryptedSharedPreferences = EncryptedSharedPreferences.create(
        context,
        "secret_shared_prefs",
        masterKeyAlias,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    private val _intent : MutableStateFlow<EncryptedState> = MutableStateFlow(EncryptedState())
    val intent = _intent.asStateFlow()


    fun processIntent(intent: EncryptionViewIntent) {
        when(intent){
            is EncryptionViewIntent.TextChangedEncrypt -> {
                val currentState = _intent.value
                Timber.d("Text to encrypt: ${intent.text}")
                _intent.value = currentState.copy(inputTextEncrypt = intent.text)
            }
            is EncryptionViewIntent.TextChangedDecrypt -> {
                //TODO: not used
            }
        }
    }


    fun encryptedInEncryptedSharedPreferences(text: String) = viewModelScope.launch {
        val editor = encryptedSharedPreferences.edit()
        editor.putString("encrypted_text", text)
        editor.apply()
        Timber.d("Text encrypted in EncryptedSharedPreferences: $text")
    }

    fun getEncryptedTextFromEncryptedSharedPreferences() = encryptedSharedPreferences.getString("encrypted_text", "No text found")

}