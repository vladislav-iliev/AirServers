package com.vladislaviliev.oauth.pkce

import java.security.MessageDigest
import java.security.SecureRandom
import java.util.Base64

class PkceGen {

    private fun genVerifier() = ByteArray(32).also { SecureRandom().nextBytes(it) }

    private fun genChallenge(verifier: ByteArray) = MessageDigest.getInstance("SHA-256").digest(verifier)

    fun genPixy(): PkcePair {
        val verifier = genVerifier()
        val challenge = genChallenge(verifier)
        fun encode(bytes: ByteArray) = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes)
        return PkcePair(encode(challenge), encode(verifier))
    }
}
