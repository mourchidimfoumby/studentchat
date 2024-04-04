package com.example.data

import com.example.data.remote.ConversationRemoteDataSource
import com.example.data.remote.UserRemoteDataSource
import com.example.data.remote.api.ConversationApi
import com.example.data.remote.api.ConversationApiImpl
import com.example.data.remote.api.UserApi
import com.example.data.remote.api.UserApiImpl
import com.example.data.repository.ConversationRepository
import com.example.data.repository.ConversationRepositoryImpl
import com.example.data.repository.UserRepository
import com.example.data.repository.UserRepositoryImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module {
    singleOf(::ConversationRepositoryImpl) { bind<ConversationRepository>() }
    singleOf(::ConversationApiImpl) { bind<ConversationApi>() }
    singleOf(::ConversationRemoteDataSource)

    singleOf(::UserRepositoryImpl) { bind<UserRepository>() }
    singleOf(::UserApiImpl) { bind<UserApi>() }
    singleOf(::UserRemoteDataSource)

}