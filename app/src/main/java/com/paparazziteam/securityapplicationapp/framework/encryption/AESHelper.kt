package com.paparazziteam.securityapplicationapp.framework.encryption

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import timber.log.Timber
import java.io.IOException
import java.security.GeneralSecurityException
import java.security.KeyStore
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.IvParameterSpec
import kotlin.jvm.Throws

object AESHelper {

    //Algorithm
    private const val ALGORITHM = "AES"
    private const val CIPHER_MODE = "GCM"
    private const val PADDING = "NoPadding"
    private const val KEY_SIZE = 256
    private const val TRANSFORMATION = "$ALGORITHM/$CIPHER_MODE/$PADDING"

    //Save the key
    private const val KEY_ALIAS = "AESKeyGCM"
    private const val ANDROID_KEY_STORE = "AndroidKeyStore"


    init {
        getKetOrCreate()
    }

    private fun generateKey(): SecretKey {
        //Generate a key and save it in the keystore
       val keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, ANDROID_KEY_STORE)
        keyGenerator.init(
            KeyGenParameterSpec.Builder(KEY_ALIAS, KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
                .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                .setKeySize(KEY_SIZE)
                .build()
        )
        return keyGenerator.generateKey()
    }

    private fun getKey(): SecretKey? {
        val keyStore = KeyStore.getInstance(ANDROID_KEY_STORE)
        keyStore.load(null)
        return keyStore.getKey(KEY_ALIAS, null) as SecretKey?
    }

    fun getKetOrCreate(): SecretKey {
        return getKey() ?: generateKey()
    }


    fun encryptText(text:String, key: SecretKey): String {
        try {
            val cipher = Cipher.getInstance(TRANSFORMATION)
            cipher.init(Cipher.ENCRYPT_MODE, key)
            val encryption =  cipher.doFinal(text.toByteArray())
            return Base64.encodeToString(encryption, Base64.DEFAULT) ?: ""
        } catch (e: Throwable) {
            Timber.e(e)
            return ""
        } catch (e: GeneralSecurityException) {
            Timber.e(e)
            return ""
        }
    }

    fun decryptText(encryptedText: String, key: SecretKey): String {
        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.DECRYPT_MODE, key)
        val decryptedText = cipher.doFinal(encryptedText.toByteArray())
        return String(decryptedText)
    }

}