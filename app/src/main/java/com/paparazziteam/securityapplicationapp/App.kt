package com.paparazziteam.securityapplicationapp

import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.paparazziteam.securityapplicationapp.utils.RecaptchaManager
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App():Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        MobileAds.initialize(this) { }
        RecaptchaManager.initialize(this)
    }
}