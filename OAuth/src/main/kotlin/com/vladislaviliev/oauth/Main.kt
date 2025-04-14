package com.vladislaviliev.oauth

import com.vladislaviliev.oauth.pkce.PkceGen
import org.slf4j.LoggerFactory
import org.springframework.boot.runApplication

fun main() {
    val logger = LoggerFactory.getLogger("Pkce")
    logger.info(PkceGen().genPixy().toString())
    runApplication<App>()
}