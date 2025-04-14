package com.vladislaviliev.oauth

import com.vladislaviliev.oauth.pkce.PkceGen
import org.springframework.boot.runApplication

fun main() {
    PkceGen().logPixy()
    runApplication<App>()
}