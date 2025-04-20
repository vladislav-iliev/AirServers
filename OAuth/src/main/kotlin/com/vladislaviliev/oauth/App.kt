package com.vladislaviliev.oauth

import com.vladislaviliev.oauth.pkce.PkceGen
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class App {

    @Bean
    fun logPkce() = ApplicationRunner {
        PkceGen().logPixy()
    }
}