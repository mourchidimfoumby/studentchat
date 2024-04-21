package com.example.data.mapper

internal interface DataMapper<Local, Domain, Remote> {
    suspend fun remoteToLocal(remote: Remote): Local
    suspend fun domainToLocal(domain: Domain): Local
    suspend fun localToRemote(local: Local): Remote
    suspend fun localToDomain(local: Local): Domain
}