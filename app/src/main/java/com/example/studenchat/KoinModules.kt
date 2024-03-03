package com.example.studenchat

import com.example.studenchat.authentication.domain.LogOutUseCase
import com.example.studenchat.chat.data.MessageRepository
import com.example.studenchat.chat.data.MessageRepositoryImpl
import com.example.studenchat.chat.domain.FormatTimestampUseCase
import com.example.studenchat.chat.domain.TimestampToLocalDate
import com.example.studenchat.chat.domain.GetAllMessageUseCase
import com.example.studenchat.chat.domain.GetLastMessageUseCase
import com.example.studenchat.chat.domain.SendMessageUseCase
import com.example.studenchat.chat.ui.stateholder.ChatViewModel
import com.example.studenchat.conversation.data.ConversationRepositoryImpl
import com.example.studenchat.conversation.data.UserConversationRepository
import com.example.studenchat.conversation.data.UserConversationRepositoryImpl
import com.example.studenchat.conversation.domain.ConvertConversationDTOUseCase
import com.example.studenchat.conversation.domain.CreateConversationUseCase
import com.example.studenchat.conversation.domain.DeleteConversationUseCase
import com.example.studenchat.conversation.domain.GetAllConversationUseCase
import com.example.studenchat.conversation.ui.stateholder.ConversationViewModel
import com.example.studenchat.friends.data.FriendsRepository
import com.example.studenchat.friends.data.FriendsRepositoryImpl
import com.example.studenchat.friends.domain.AddFriendsUseCase
import com.example.studenchat.friends.domain.GetAllFriendsUseCase
import com.example.studenchat.friends.domain.GetAllNotFriendsUseCase
import com.example.studenchat.friends.ui.stateholder.FriendsViewModel
import com.example.studenchat.user.data.UserRepository
import com.example.studenchat.user.data.UserRepositoryImpl
import com.example.studenchat.user.domain.GetCurrentUserUseCase
import com.example.studenchat.user.domain.GetUserWithUidUseCase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::UserConversationRepositoryImpl) { bind<UserConversationRepository>() }
    singleOf(::UserRepositoryImpl) { bind<UserRepository>() }
    singleOf(::FriendsRepositoryImpl) { bind<FriendsRepository>() }
    singleOf(::MessageRepositoryImpl) { bind<MessageRepository>() }
    factoryOf(::ConversationRepositoryImpl)

    factoryOf(::GetAllConversationUseCase)
    factoryOf(::CreateConversationUseCase)
    factoryOf(::DeleteConversationUseCase)
    factoryOf(::ConvertConversationDTOUseCase)
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

    factoryOf(::RemoveListenerUseCase)
    factoryOf(::LogOutUseCase)

    factoryOf(::ConversationViewModel)
    factoryOf(::FriendsViewModel)
    factoryOf(::ChatViewModel)
}