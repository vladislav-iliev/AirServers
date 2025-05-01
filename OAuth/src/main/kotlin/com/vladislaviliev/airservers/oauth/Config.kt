package com.vladislaviliev.airservers.oauth

import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jose.jwk.RSAKey
import com.nimbusds.jose.jwk.source.ImmutableJWKSet
import com.nimbusds.jose.jwk.source.JWKSource
import com.nimbusds.jose.proc.SecurityContext
import com.vladislaviliev.airservers.oauth.credentials.authority.AuthorityDao
import com.vladislaviliev.airservers.oauth.credentials.salt.Salt
import com.vladislaviliev.airservers.oauth.credentials.salt.SaltDao
import com.vladislaviliev.airservers.oauth.credentials.user.UserDao
import com.vladislaviliev.airservers.oauth.credentials.userAuthority.UserAuthorityDao
import com.vladislaviliev.airservers.oauth.userManager.UserManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.core.AuthorizationGrantType
import org.springframework.security.oauth2.core.ClientAuthenticationMethod
import org.springframework.security.oauth2.core.oidc.OidcScopes
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings
import org.springframework.security.web.SecurityFilterChain
import java.security.KeyPairGenerator
import java.security.SecureRandom
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.util.*

@Configuration
@PropertySource("classpath:secrets.properties")
class Config {

    @Bean
    fun userDetailsManager(
        userDao: UserDao,
        authorityDao: AuthorityDao,
        userAuthorityDao: UserAuthorityDao,
    ) = UserManager(userDao, authorityDao, userAuthorityDao)

    @Bean
    fun passwordEncoder(saltDao: SaltDao): PasswordEncoder {
        val salt = saltDao.findByIdOrNull(Salt.USER_PASSWORD)
        val strength = 4
        return if (salt == null)
            BCryptPasswordEncoder(strength)
        else
            BCryptPasswordEncoder(strength, SecureRandom(salt.valuation.toByteArray()))
    }

    @Bean
    fun registeredClientRepository(): RegisteredClientRepository {
        val registeredClient = RegisteredClient
            .withId(UUID.randomUUID().toString())
            .clientId("some_client")
            .clientSecret("some_client_secret")
            .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
            .redirectUri("https://www.motherfuckingwebsite.com")
            .scope(OidcScopes.OPENID)
            .build()
        return InMemoryRegisteredClientRepository(registeredClient)
    }

    @Bean
    fun jwkSource(): JWKSource<SecurityContext> {
        val keyPairGenerator = KeyPairGenerator.getInstance("RSA").apply { initialize(2048) }
        val keyPair = keyPairGenerator.generateKeyPair()
        val publicKey = keyPair.public as RSAPublicKey
        val privateKey = keyPair.private as RSAPrivateKey
        val rsaKey = RSAKey.Builder(publicKey).privateKey(privateKey).keyID(UUID.randomUUID().toString()).build()
        val jwkSet = JWKSet(rsaKey)
        return ImmutableJWKSet(jwkSet)
    }

    @Bean
    fun httpFilters(http: HttpSecurity): SecurityFilterChain {
        http.with(
            OAuth2AuthorizationServerConfigurer.authorizationServer().oidc(Customizer.withDefaults()),
            Customizer.withDefaults()
        )
        http.formLogin(Customizer.withDefaults())
        http.authorizeHttpRequests { it.anyRequest().authenticated() }
        return http.build()
    }

    @Bean
    fun authorisationServerSettings() = AuthorizationServerSettings.builder().build()
}