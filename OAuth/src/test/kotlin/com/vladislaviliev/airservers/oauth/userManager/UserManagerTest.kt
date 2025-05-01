package com.vladislaviliev.airservers.oauth.userManager

import com.vladislaviliev.airservers.oauth.credentials.authority.AuthorityDao
import com.vladislaviliev.airservers.oauth.credentials.authority.PersistedAuthority
import com.vladislaviliev.airservers.oauth.credentials.user.UserDao
import com.vladislaviliev.airservers.oauth.credentials.userAuthority.UserAuthorityDao
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.core.userdetails.User
import org.springframework.security.provisioning.UserDetailsManager

@SpringBootTest()
@AutoConfigureTestDatabase
class UserManagerTest @Autowired constructor(
    private val userDao: UserDao,
    private val authorityDao: AuthorityDao,
    private val userAuthorityDao: UserAuthorityDao,
    private val userDetailsManager: UserDetailsManager,
) {

    private fun addAuthority(vararg names: String) {
        authorityDao.saveAll(names.map { PersistedAuthority(it) })
    }

    @Test
    fun testCreateUser() {
        addAuthority("test_authority")
        val u = User.withUsername("test_user").password("test_password").authorities("test_authority").build()
        userDetailsManager.createUser(u)

        val loadedU = userDao.findByUsername("test_user")
        Assertions.assertEquals(u.username, loadedU.username)
        Assertions.assertEquals(u.password, loadedU.password)

        val loadedAuths = userAuthorityDao.findByIdUserId(loadedU.id).map { it.authority.name }
        Assertions.assertEquals(1, loadedAuths.size)
        Assertions.assertEquals("test_authority", loadedAuths.first())
    }

    @Test
    fun testUpdateUser() {
        addAuthority("test_authority")
        val u = User.withUsername("test_user").password("test_password").authorities("test_authority").build()
        userDetailsManager.createUser(u)

        addAuthority("new_test_authority")
        val newDetails =
            User.withUsername("test_user").password("new_test_password").authorities("new_test_authority").build()
        userDetailsManager.updateUser(newDetails)

        val loadedU = userDao.findByUsername("test_user")
        Assertions.assertEquals(newDetails.password, loadedU.password)

        val auths = userAuthorityDao.findByIdUserId(loadedU.id).map { it.authority.name }
        Assertions.assertEquals(1, auths.count())
        Assertions.assertEquals("new_test_authority", auths.first())
    }
}