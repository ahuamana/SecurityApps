package com.paparazziteam.securityapplicationapp.framework.adb

import android.content.Context
import android.provider.Settings

class AdbDetectorManager(private val context: Context) {

    private fun getAdbState(): AdbState {
        val adbValue = Settings.Secure.getInt(
            context.contentResolver,
            Settings.Global.ADB_ENABLED,
            0
        )
        return AdbState.from(adbValue)
    }

    fun isAdbEnabledState():Boolean {
        return getAdbState() is AdbState.Enabled
    }
}