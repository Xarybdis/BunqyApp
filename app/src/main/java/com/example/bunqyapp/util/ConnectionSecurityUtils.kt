package com.example.bunqyapp.util

import android.util.Base64
import android.util.Log
import timber.log.Timber
import java.lang.Exception
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.NoSuchAlgorithmException
import java.security.Signature

class ConnectionSecurityUtils {

    val keyPair = keyGenerator()

    private fun keyGenerator(): KeyPair? {
        var keyPair: KeyPair? = null

        try {
            val generator: KeyPairGenerator =
                KeyPairGenerator.getInstance(KEY_PAIR_GENERATOR_ALGORITHM)
            keyPair = generator.generateKeyPair()
        } catch (e: NoSuchAlgorithmException) {
            Timber.e("Security problem occurred: ${e.localizedMessage}")
        }
        return keyPair
    }

    fun createPKCS8StandardPublicKey(key: KeyPair?): String {
        val publicEncodedKey = key?.public?.encoded

        if (publicEncodedKey != null) {
            val publicKey: String = Base64.encodeToString(publicEncodedKey, Base64.DEFAULT)
            return "$PUBLIC_KEY_START$publicKey$PUBLIC_KEY_END"
        }
        return ""
    }

    fun unWrapThePublicKey(key: KeyPair?): String {
        val unWrappedKey = key?.public?.encoded

        if (unWrappedKey != null) {
            val newKey: String = Base64.encodeToString(unWrappedKey, Base64.NO_WRAP)
            return newKey
        }
        return ""
    }

    companion object {
        private const val KEY_PAIR_GENERATOR_ALGORITHM = "RSA"
        private const val SIGNATURE_ALGORITHM = "SHA256withRSA"
        private const val KEY_PAIR_GENERATOR_KEY_SIZE = 2048

        const val PUBLIC_KEY_START = "-----BEGIN PUBLIC KEY-----\n"
        const val PUBLIC_KEY_END = "-----END PUBLIC KEY-----\n"
    }
}