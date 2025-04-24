package com.vladislaviliev.airservers.oauth.credentials.salt

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SaltDao : CrudRepository<Salt, String>