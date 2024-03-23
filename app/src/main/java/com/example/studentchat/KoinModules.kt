package com.example.studentchat

import com.example.studentchat.authentication.AuthenticationManager
import com.example.studentchat.authentication.domain.IsLoggedInUseCase
import com.example.studentchat.authentication.domain.LogInWithEmailPasswordUseCase
import com.example.studentchat.authentication.domain.LogOutUseCase
import com.example.studentchat.authentication.domain.SignUpWithEmailPasswordUseCase
import com.example.studentchat.chat.data.MessageApi
import com.example.studentchat.chat.data.MessageApiImpl
import com.example.studentchat.chat.data.MessageRemoteDataSource
import com.example.studentchat.chat.data.MessageRepository
import com.example.studentchat.chat.data.MessageRepositoryImpl
import com.example.studentchat.chat.domain.FormatTimestampUseCase
import com.example.studentchat.chat.domain.GetAllMessageUseCase
import com.example.studentchat.chat.domain.GetLastMessageUseCase
import com.example.studentchat.chat.domain.SendMessageUseCase
import com.example.studentchat.chat.domain.TimestampToLocalDate
import com.example.studentchat.chat.ui.stateholder.ChatViewModel
import com.example.studentchat.conversation.data.ConversationApi
import com.example.studentchat.conversation.data.ConversationApiImpl
import com.example.studentchat.conversation.data.ConversationRemoteDataSource
import com.example.studentchat.conversation.data.ConversationRepository
import com.example.studentchat.conversation.data.ConversationRepositoryImpl
import com.example.studentchat.conversation.domain.ConvertConversationUseCase
import com.example.studentchat.conversation.domain.CreateConversationUseCase
import com.example.studentchat.conversation.domain.DeleteConversationUseCase
import com.example.studentchat.conversation.domain.GetAllConversationsUseCase
import com.example.studentchat.conversation.ui.stateholder.ConversationViewModel
import com.example.studentchat.friends.data.FriendsApi
import com.example.studentchat.friends.data.FriendsApiImpl
import com.example.studentchat.friends.data.FriendsRemoteDataSource
import com.example.studentchat.friends.data.FriendsRepository
import com.example.studentchat.friends.data.FriendsRepositoryImpl
import com.example.studentchat.friends.domain.AddFriendsUseCase
import com.example.studentchat.friends.domain.GetAllFriendsUseCase
import com.example.studentchat.friends.domain.GetAllNotFriendsUseCase
import com.example.studentchat.friends.ui.stateholder.FriendsViewModel
import com.example.studentchat.user.data.UserApi
import com.example.studentchat.user.data.UserApiImpl
import com.example.studentchat.user.data.UserRemoteDataSource
import com.example.studentchat.user.data.UserRepository
import com.example.studentchat.user.data.UserRepositoryImpl
import com.example.studentchat.user.domain.GetCurrentUserUseCase
import com.example.studentchat.user.domain.GetUserUseCase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::ConversationRepositoryImpl) { bind<ConversationRepository>() }
    singleOf(::MessageRepositoryImpl) { bind<MessageRepository>() }
    singleOf(::UserRepositoryImpl) { bind<UserRepository>() }
    singleOf(::FriendsRepositoryImpl) { bind<FriendsRepository>() }

    singleOf(::ConversationApiImpl) { bind<ConversationApi>() }
    singleOf(::MessageApiImpl) { bind<MessageApi>() }
    singleOf(::UserApiImpl) { bind<UserApi>() }
    singleOf(::FriendsApiImpl) { bind<FriendsApi>() }

    singleOf(::ConversationRemoteDataSource)
    singleOf(::MessageRemoteDataSource)
    singleOf(::UserRemoteDataSource)
    singleOf(::FriendsRemoteDataSource)

    singleOf(::GetAllConversationsUseCase)
    singleOf(::ConvertConversationUseCase)
    singleOf(::CreateConversationUseCase)
    singleOf(::DeleteConversationUseCase)
    singleOf(::RemoveListenerUseCase)

    singleOf(::GetUserUseCase)
    singleOf(::GetCurrentUserUseCase)
    singleOf(::AddFriendsUseCase)
    singleOf(::GetAllFriendsUseCase)
    singleOf(::GetAllNotFriendsUseCase)

    singleOf(::SendMessageUseCase)
    singleOf(::GetAllMessageUseCase)
    singleOf(::GetLastMessageUseCase)
    singleOf(::TimestampToLocalDate)
    singleOf(::FormatTimestampUseCase)

    singleOf(::IsLoggedInUseCase)
    singleOf(::LogOutUseCase)
    singleOf(::LogInWithEmailPasswordUseCase)
    singleOf(::SignUpWithEmailPasswordUseCase)
    singleOf(::AuthenticationManager)

    singleOf(::RemoveListenerUseCase)

    singleOf(::ConversationViewModel)
    singleOf(::FriendsViewModel)
    singleOf(::ChatViewModel)
}