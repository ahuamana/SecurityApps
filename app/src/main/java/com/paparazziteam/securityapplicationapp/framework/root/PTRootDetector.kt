package com.paparazziteam.securityapplicationapp.framework.root

import android.content.Context
import com.scottyab.rootbeer.RootBeer

class PTRootDetector(private val context: Context) {

    fun isRootedWithBusyBoxCheck():Boolean {
        val rootChecker = RootBeer(context)
        return rootChecker.isRootedWithBusyBoxCheck
    }

    fun isRootNormal():Boolean {
        val rootChecker = RootBeer(context)
        return rootChecker.isRooted
    }
}