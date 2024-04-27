package com.paparazziteam.securityapplicationapp.utils

import android.app.Application
import com.google.android.recaptcha.Recaptcha
import com.google.android.recaptcha.RecaptchaAction
import com.google.android.recaptcha.RecaptchaClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.time.Duration.Companion.seconds


object RecaptchaManager {

    private lateinit var recaptchaClient: RecaptchaClient
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)

    private fun getSiteKey(): String {
        return "6LdYLaApAAAAAMmHWLLWqLbFllCXWz7XoE852OZ1"
    }

    fun initialize(application: Application) {
        coroutineScope.launch(Dispatchers.IO) {
            Recaptcha.getClient(application, getSiteKey())
                .onSuccess {
                    // Handle success
                    recaptchaClient = it
                    delay(3.seconds)
                    executeRecaptcha()
                }.onFailure {
                    // Handle failure
                    Timber.d("Recaptcha client -- failure $it")
                }
        }
    }

    private fun executeRecaptcha() {
        coroutineScope.launch(Dispatchers.IO) {
            recaptchaClient.execute(RecaptchaAction.LOGIN)
                .onSuccess {
                    // Handle success
                    Timber.d("Recaptcha success $it")
                }.onFailure {
                    // Handle failure
                    Timber.d("Recaptcha failure $it")
                }
        }
    }

}