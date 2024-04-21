package com.example.data.mapper

import com.example.data.local.entity.FriendsEntity
import com.example.data.model.Friends
import com.example.data.remote.model.FriendsRemote

internal class FriendsDataMapper : DataMapper<FriendsEntity, Friends, FriendsRemote> {
    override suspend fun remoteToLocal(remote: FriendsRemote): FriendsEntity {
        TODO("Not yet implemented")
    }

    override suspend fun domainToLocal(domain: Friends): FriendsEntity {
        TODO("Not yet implemented")
    }

    override suspend fun localToRemote(local: FriendsEntity): FriendsRemote {
        TODO("Not yet implemented")
    }

    override suspend fun localToDomain(local: FriendsEntity): Friends {
        TODO("Not yet implemented")
    }

}