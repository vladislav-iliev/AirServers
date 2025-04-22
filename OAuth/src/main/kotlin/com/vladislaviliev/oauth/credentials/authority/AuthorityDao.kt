package com.vladislaviliev.oauth.credentials.authority

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorityDao : CrudRepository<PersistedAuthority, Long>