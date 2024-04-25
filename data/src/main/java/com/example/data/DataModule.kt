package com.example.data

import androidx.room.Room
import com.example.data.local.ConversationLocalDataSource
import com.example.data.local.FriendsLocalDataSource
import com.example.data.local.MessageLocalDataSource
import com.example.data.local.UserLocalDataSource
import com.example.data.local.datastore.ObjectDataStore
import com.example.data.local.datastore.user.UserDataStore
import com.example.data.local.datastore.user.UserLocal
import com.example.data.mapper.ConversationDataMapper
import com.example.data.mapper.FriendsDataMapper
import com.example.data.mapper.MessageDataMapper
import com.example.data.mapper.UserDataMapper
import com.example.data.remote.ConversationRemoteDataSource
import com.example.data.remote.FriendsRemoteDataSource
import com.example.data.remote.MessageRemoteDataSource
import com.example.data.remote.UserRemoteDataSource
import com.example.data.remote.api.ConversationApi
import com.example.data.remote.api.ConversationApiImpl
import com.example.data.remote.api.FriendsApi
import com.example.data.remote.api.FriendsApiImpl
import com.example.data.remote.api.MessageApi
import com.example.data.remote.api.MessageApiImpl
import com.example.data.remote.api.UserApi
import com.example.data.remote.api.UserApiImpl
import com.example.data.repository.ConversationRepository
import com.example.data.repository.ConversationRepositoryImpl
import com.example.data.repository.FriendsRepository
import com.example.data.repository.FriendsRepositoryImpl
import com.example.data.repository.MessageRepository
import com.example.data.repository.MessageRepositoryImpl
import com.example.data.repository.UserRepository
import com.example.data.repository.UserRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

private const val STUDENT_CHAT_DATABASE = "StudentChatDatabase"

val dataModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            StudentChatDatabase::class.java,
            STUDENT_CHAT_DATABASE
        ).build()
    }

    single { get<StudentChatDatabase>().conversationDao() }
    single { get<StudentChatDatabase>().friendsDao() }
    single { get<StudentChatDatabase>().messageDao() }

    singleOf(::ConversationRepositoryImpl) { bind<ConversationRepository>() }
    singleOf(::ConversationApiImpl) { bind<ConversationApi>() }
    singleOf(::ConversationRemoteDataSource)
    singleOf(::ConversationLocalDataSource)

    singleOf(::UserRepositoryImpl) { bind<UserRepository>() }
    singleOf(::UserApiImpl) { bind<UserApi>() }
    singleOf(::UserRemoteDataSource)
    singleOf(::UserLocalDataSource)
    singleOf(::UserDataStore) { bind<ObjectDataStore<UserLocal>>() }

    singleOf(::FriendsRepositoryImpl) { bind<FriendsRepository>() }
    singleOf(::FriendsApiImpl) { bind<FriendsApi>() }
    singleOf(::FriendsRemoteDataSource)
    singleOf(::FriendsLocalDataSource)

    singleOf(::MessageRepositoryImpl) { bind<MessageRepository>() }
    singleOf(::MessageApiImpl) { bind<MessageApi>() }
    singleOf(::MessageRemoteDataSource)
    singleOf(::MessageLocalDataSource)

    singleOf(::ConversationDataMapper)
    singleOf(::UserDataMapper)
    singleOf(::FriendsDataMapper)
    singleOf(::MessageDataMapper)
    singleOf(::MessageDataMapper)

}