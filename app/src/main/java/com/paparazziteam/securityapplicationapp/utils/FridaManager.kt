package com.paparazziteam.securityapplicationapp.utils

//Generate a class to detect Frida, this class will be used in the app to detect frida and return a boolean
//Also, this class will be used in the server to detect frida and return a boolean

class FridaDetector {

    fun isFridaServerRunning(): Boolean {
        return false
    }

    fun isFridaAppRunning(): Boolean {
        return false
    }


}