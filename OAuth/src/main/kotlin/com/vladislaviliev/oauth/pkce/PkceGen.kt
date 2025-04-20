package com.vladislaviliev.oauth.pkce

import org.slf4j.LoggerFactory
import org.springframework.security.crypto.keygen.KeyGenerators
import java.security.MessageDigest
import java.util.*

class PkceGen {

    private fun genVerifier() = KeyGenerators.secureRandom(32).generateKey()

    private fun genChallenge(verifier: ByteArray) = MessageDigest.getInstance("SHA-256").digest(verifier)

    private fun genPixy(): PkcePair {
        val verifier = genVerifier()
        val challenge = genChallenge(verifier)
        fun encode(bytes: ByteArray) = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes)
        return PkcePair(encode(challenge), encode(verifier))
    }

    fun logPixy() = LoggerFactory.getLogger(this::class.simpleName).info(genPixy().toString())
}
