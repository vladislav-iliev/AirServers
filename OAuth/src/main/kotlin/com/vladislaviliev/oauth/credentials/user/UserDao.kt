package com.vladislaviliev.oauth.credentials.user

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserDao : CrudRepository<PersistedUser, Long>