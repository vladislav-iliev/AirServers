package com.vladislaviliev.airservers.resource

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@PropertySource("classpath:secrets.properties")
class Config {

    @Bean
    fun httpFilters(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests { it.requestMatchers(HttpMethod.GET).permitAll().anyRequest().authenticated() }
        return http.build()
    }

}