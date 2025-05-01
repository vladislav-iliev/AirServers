package com.vladislaviliev.airservers.oauth.credentials.user

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserDao : CrudRepository<PersistedUser, Long> {
    fun findByUsername(username: String): PersistedUser
    fun existsByUsername(username: String): Boolean
    fun deleteByUsername(username: String)
}