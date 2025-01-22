package com.paparazziteam.securityapplicationapp.presentation.screens.screens.frida

import android.content.Context
import androidx.lifecycle.ViewModel
import com.paparazziteam.securityapplicationapp.framework.adb.AdbDetectorManager
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class FridaBottomViewModel @Inject constructor(
    @ApplicationContext val context: Context
) : ViewModel() {


    fun checkAdb():Boolean {
        val manager = AdbDetectorManager(context)
        val isActive = manager.isAdbEnabledState()
        return isActive
    }

}