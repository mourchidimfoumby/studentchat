package com.example.data.mapper

import com.example.data.local.datastore.user.UserLocal
import com.example.data.model.Friends
import com.example.data.model.User
import com.example.data.remote.model.UserRemote

internal class UserDataMapper {
    fun remoteToLocal(remote: UserRemote): UserLocal =
        UserLocal(
            remote.uid,
            remote.firstName,
            remote.lastName,
            remote.mail,
            remote.password,
            remote.birthday
        )

    fun remoteToDomain(remote: UserRemote): User =
        User(
            remote.uid,
            remote.firstName,
            remote.lastName,
            remote.mail,
            remote.password,
            remote.birthday
        )

    fun domainToLocal(domain: User): UserLocal =
        UserLocal(
            domain.uid,
            domain.firstName,
            domain.lastName,
            domain.mail,
            domain.password,
            domain.birthday
        )

    fun localToRemote(local: UserLocal): UserRemote =
        UserRemote(
            local.uid,
            local.firstName,
            local.lastName,
            local.mail,
            local.password,
            local.birthday
        )

    fun localToDomain(local: UserLocal): User =
        User(
            local.uid,
            local.firstName,
            local.lastName,
            local.mail,
            local.password,
            local.birthday
        )

    fun toFriends(user: User): Friends =
        Friends(
            user.uid,
            user.firstName,
            user.lastName,
            user.mail
        )
}