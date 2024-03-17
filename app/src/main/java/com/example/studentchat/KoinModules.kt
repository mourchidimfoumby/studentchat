package com.example.studentchat

import com.example.studentchat.authentication.domain.LogOutUseCase
import com.example.studentchat.chat.data.MessageRepository
import com.example.studentchat.chat.data.MessageRepositoryImpl
import com.example.studentchat.chat.domain.FormatTimestampUseCase
import com.example.studentchat.chat.domain.TimestampToLocalDate
import com.example.studentchat.chat.domain.GetAllMessageUseCase
import com.example.studentchat.chat.domain.GetLastMessageUseCase
import com.example.studentchat.chat.domain.SendMessageUseCase
import com.example.studentchat.chat.ui.stateholder.ChatViewModel
import com.example.studentchat.conversation.data.ConversationApi
import com.example.studentchat.conversation.data.ConversationApiImpl
import com.example.studentchat.conversation.data.ConversationRemoteDataSource
import com.example.studentchat.conversation.data.ConversationRepository
import com.example.studentchat.conversation.data.ConversationRepositoryImpl
import com.example.studentchat.conversation.data.UserConversationRepository
import com.example.studentchat.conversation.data.UserConversationRepositoryImpl
import com.example.studentchat.conversation.domain.ConvertConversationDTOUseCase
import com.example.studentchat.conversation.domain.CreateConversationUseCase
import com.example.studentchat.conversation.domain.DeleteConversationUseCase
import com.example.studentchat.conversation.ui.stateholder.ConversationViewModel
import com.example.studentchat.friends.data.FriendsRepository
import com.example.studentchat.friends.data.FriendsRepositoryImpl
import com.example.studentchat.friends.domain.AddFriendsUseCase
import com.example.studentchat.friends.domain.GetAllFriendsUseCase
import com.example.studentchat.friends.domain.GetAllNotFriendsUseCase
import com.example.studentchat.friends.ui.stateholder.FriendsViewModel
import com.example.studentchat.user.data.UserRepository
import com.example.studentchat.user.data.UserRepositoryImpl
import com.example.studentchat.user.domain.GetCurrentUserUseCase
import com.example.studentchat.user.domain.GetUserWithUidUseCase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::UserConversationRepositoryImpl) { bind<UserConversationRepository>() }
    singleOf(::UserRepositoryImpl) { bind<UserRepository>() }
    singleOf(::FriendsRepositoryImpl) { bind<FriendsRepository>() }
    singleOf(::MessageRepositoryImpl) { bind<MessageRepository>() }
    singleOf(::ConversationRepositoryImpl) { bind<ConversationRepository>()}

    singleOf(::ConversationApiImpl) { bind<ConversationApi>() }
    singleOf(::ConversationRemoteDataSource)

    singleOf(::ConvertConversationDTOUseCase)
    singleOf(::RemoveListenerUseCase)
    factoryOf(::CreateConversationUseCase)
    factoryOf(::DeleteConversationUseCase)
    factoryOf(::RemoveListenerUseCase)

    factoryOf(::GetUserWithUidUseCase)
    factoryOf(::GetCurrentUserUseCase)
    factoryOf(::AddFriendsUseCase)
    factoryOf(::GetAllFriendsUseCase)
    factoryOf(::GetAllNotFriendsUseCase)

    factoryOf(::SendMessageUseCase)
    factoryOf(::GetAllMessageUseCase)
    factoryOf(::GetLastMessageUseCase)
    factoryOf(::TimestampToLocalDate)
    factoryOf(::FormatTimestampUseCase)

    factoryOf(::LogOutUseCase)

    factoryOf(::ConversationViewModel)
    factoryOf(::FriendsViewModel)
    factoryOf(::ChatViewModel)
}