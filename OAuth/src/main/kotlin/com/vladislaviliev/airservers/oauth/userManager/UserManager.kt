package com.vladislaviliev.airservers.oauth.userManager

import com.vladislaviliev.airservers.oauth.credentials.authority.AuthorityDao
import com.vladislaviliev.airservers.oauth.credentials.user.PersistedUser
import com.vladislaviliev.airservers.oauth.credentials.user.UserDao
import com.vladislaviliev.airservers.oauth.credentials.userAuthority.PersistedUserAuthority
import com.vladislaviliev.airservers.oauth.credentials.userAuthority.PersistedUserAuthorityId
import com.vladislaviliev.airservers.oauth.credentials.userAuthority.UserAuthorityDao
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsPasswordService
import org.springframework.security.provisioning.UserDetailsManager

class UserManager(
    private val userDao: UserDao,
    private val authorityDao: AuthorityDao,
    private val userAuthorityDao: UserAuthorityDao,
) : UserDetailsManager, UserDetailsPasswordService {

    override fun createUser(user: UserDetails) {
        val savedU = userDao.save(PersistedUser(user.username, user.password))

        val userAuthsToSave = user.authorities.map {
            val authId = authorityDao.findByName(it.authority).id
            PersistedUserAuthority(PersistedUserAuthorityId(savedU.id, authId))
        }

        userAuthorityDao.saveAll(userAuthsToSave)
    }

    override fun updateUser(user: UserDetails) {
        val savedU = userDao.findByUsername(user.username)
        userDao.save(PersistedUser(savedU.id, user.username, user.password))

        val newAuths = user.authorities.map { authorityDao.findByName(it.authority) }
        val savedAuths = userAuthorityDao.findByIdUserId(savedU.id).map { it.authority }

        val toAdd = newAuths - savedAuths
        val intersecting = savedAuths.intersect(newAuths)
        val toRemove = savedAuths - intersecting

        userAuthorityDao.saveAll(toAdd.map {
            PersistedUserAuthority(PersistedUserAuthorityId(savedU.id, it.id))
        })
        userAuthorityDao.deleteAllById(toRemove.map { PersistedUserAuthorityId(savedU.id, it.id) })
    }

    override fun deleteUser(username: String) {
        userDao.deleteByUsername(username)
    }

    override fun changePassword(oldPassword: String, newPassword: String) {
        val user = SecurityContextHolder.getContext().authentication!!.principal as UserDetails
        val savedU = userDao.findByUsername(user.username)
        userDao.save(PersistedUser(savedU.id, savedU.username, newPassword))
    }

    override fun updatePassword(user: UserDetails, newPassword: String): UserDetails {
        val savedU = userDao.findByUsername(user.username)
        userDao.save(PersistedUser(savedU.id, savedU.username, newPassword))
        return user
    }

    override fun userExists(username: String): Boolean {
        return userDao.existsByUsername(username)
    }

    override fun loadUserByUsername(username: String): UserDetails {
        val savedU = userDao.findByUsername(username)
        val savedAuths = userAuthorityDao.findByIdUserId(savedU.id).map { persistedUserAuth ->
            GrantedAuthority { persistedUserAuth.authority.name }
        }
        return User.builder().username(savedU.username).password(savedU.password).authorities(savedAuths).build()
    }
}