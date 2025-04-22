package com.vladislaviliev.oauth.credentials.userAuthority

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserAuthorityDao : CrudRepository<PersistedUserAuthority, Long>