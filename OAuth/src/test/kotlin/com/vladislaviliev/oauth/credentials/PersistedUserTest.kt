package com.vladislaviliev.oauth.credentials

import com.vladislaviliev.oauth.credentials.authority.AuthorityDao
import com.vladislaviliev.oauth.credentials.authority.PersistedAuthority
import com.vladislaviliev.oauth.credentials.user.PersistedUser
import com.vladislaviliev.oauth.credentials.user.UserDao
import com.vladislaviliev.oauth.credentials.userAuthority.PersistedUserAuthority
import com.vladislaviliev.oauth.credentials.userAuthority.PersistedUserAuthorityId
import com.vladislaviliev.oauth.credentials.userAuthority.UserAuthorityDao
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
@AutoConfigureTestDatabase
@EntityScan("com.vladislaviliev.oauth.credentials")
class PersistedUserTest @Autowired constructor(
    val userDao: UserDao,
    val authorityDao: AuthorityDao,
    val userAuthorityDao: UserAuthorityDao,
) {
    @Test
    fun userLoadsAuthorities() {
        var u = PersistedUser("vlady", "vlady", true)
        u = userDao.save(u)

        var a = PersistedAuthority("read")
        a = authorityDao.save(a)

        val ua = PersistedUserAuthority(PersistedUserAuthorityId(u.id, a.id))
        userAuthorityDao.save(ua)

        Assertions.assertEquals(1, userAuthorityDao.findAll().count())
    }
}