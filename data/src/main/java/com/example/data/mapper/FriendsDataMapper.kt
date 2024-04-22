package com.example.data.mapper

import com.example.data.local.entity.FriendsEntity
import com.example.data.model.Friends
import com.example.data.remote.model.FriendsRemote

internal class FriendsDataMapper {
    fun remoteToLocal(remote: FriendsRemote): FriendsEntity =
        FriendsEntity(
            remote.uid,
            remote.firstName,
            remote.lastName,
            remote.mail
        )

    fun domainToLocal(domain: Friends): FriendsEntity =
        FriendsEntity(
            domain.uid,
            domain.firstName,
            domain.lastName,
            domain.mail
        )

    fun localToRemote(local: FriendsEntity): FriendsRemote =
        FriendsRemote(
            local.uid,
            local.firstName,
            local.lastName,
            local.mail
        )

    fun localToDomain(local: FriendsEntity): Friends =
        Friends(
            local.uid,
            local.firstName,
            local.lastName,
            local.mail
        )

}