package com.paparazziteam.securityapplicationapp.framework.adb

sealed class AdbState {
    data object Enabled : AdbState()
    data object Disabled : AdbState()

    companion object {
        fun from(value: Int): AdbState = when (value) {
            1 -> Enabled
            else -> Disabled
        }
    }
}
