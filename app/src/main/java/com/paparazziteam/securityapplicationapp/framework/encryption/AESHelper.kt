package com.paparazziteam.securityapplicationapp.framework.encryption

import android.content.Context
import android.content.SharedPreferences
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.security.keystore.KeyProtection
import android.util.Base64
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException
import java.security.GeneralSecurityException
import java.security.KeyStore
import java.security.KeyStore.SecretKeyEntry
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
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
    private const val KEY_ALIAS_IV = "AESKeyGCM_IV_NEW"
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


    fun encryptText(text:String, key: SecretKey, context: Context): String {
        try {
            val cipher = Cipher.getInstance(TRANSFORMATION)
            cipher.init(Cipher.ENCRYPT_MODE, key)

            //Save Iv on Encrypted SharedPreferences
            val iv = cipher.iv
            saveDataInEncryptedSharedPreferences(iv, context)

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

    private fun saveDataInEncryptedSharedPreferences(data: ByteArray, context: Context) {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        val sharedPreferences = EncryptedSharedPreferences.create(
            context,
            "secret_shared_prefs",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

        val editor = sharedPreferences.edit()
        editor.putString(KEY_ALIAS_IV, Base64.encodeToString(data, Base64.DEFAULT))
        editor.apply()
    }



    fun decryptText(encryptedText: String, key: SecretKey, context: Context): String {
        try {
            if(encryptedText.isEmpty()) return ""
            //Iv
            val keyStore = KeyStore.getInstance(ANDROID_KEY_STORE)
            keyStore.load(null)
            val secretKey = keyStore.getKey(KEY_ALIAS, null) as SecretKey ?: return ""
            //Get Iv from Encrypted SharedPreferences
            val masterKey = MasterKey.Builder(context)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build()

            val sharedPreferences = EncryptedSharedPreferences.create(
                context,
                "secret_shared_prefs",
                masterKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM)

            val iv = sharedPreferences.getString(KEY_ALIAS_IV, "") ?: return ""
            val ivBytes = Base64.decode(iv, Base64.DEFAULT)

            val cipher = Cipher.getInstance(TRANSFORMATION)
            cipher.init(Cipher.DECRYPT_MODE, key, GCMParameterSpec(128, ivBytes))
            val decryptedText = cipher.doFinal(Base64.decode(encryptedText, Base64.DEFAULT))
            return String(decryptedText)
        } catch (e: Throwable) {
            Timber.e(e)
            return ""
        } catch (e: GeneralSecurityException) {
            Timber.e(e)
            return ""
        }
    }
}