package com.example.studenchat

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.example.studenchat.conversation.data.UserConversationRepository
import com.example.studenchat.friends.data.FriendsRepositoryImpl
import com.example.studenchat.friends.data.FriendsRepository
import com.example.studenchat.chat.data.MessageRepository
import com.example.studenchat.user.data.UserRepository
import com.example.studenchat.conversation.data.ConversationRepositoryImpl
import com.example.studenchat.chat.data.MessageRepositoryImpl
import com.example.studenchat.conversation.data.UserConversationRepositoryImpl
import com.example.studenchat.user.data.UserRepositoryImpl
import com.example.studenchat.conversation.domain.CreateConversationUseCase
import com.example.studenchat.conversation.domain.DeleteConversationUseCase
import com.example.studenchat.friends.domain.AddFriendsUseCase
import com.example.studenchat.conversation.domain.ConvertConversationDTOUseCase
import com.example.studenchat.conversation.domain.GetAllConversationUseCase
import com.example.studenchat.friends.domain.GetAllFriendsUseCase
import com.example.studenchat.friends.domain.GetAllNotFriendsUseCase
import com.example.studenchat.user.domain.GetCurrentUserUseCase
import com.example.studenchat.user.domain.GetUserWithUidUseCase
import com.example.studenchat.conversation.ui.stateholder.ConversationViewModel
import com.example.studenchat.friends.ui.stateholder.FriendsViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf

val appModule = module {
    singleOf(::UserConversationRepositoryImpl) { bind<UserConversationRepository>() }
    singleOf(::UserRepositoryImpl) { bind<UserRepository>() }
    singleOf(::FriendsRepositoryImpl) { bind<FriendsRepository>() }
    singleOf(::MessageRepositoryImpl) { bind<MessageRepository>() }
    factoryOf(::ConversationRepositoryImpl)

    factoryOf(::CreateConversationUseCase)
    factoryOf(::DeleteConversationUseCase)
    factoryOf(::GetUserWithUidUseCase)
    factoryOf(::GetCurrentUserUseCase)
    factoryOf(::AddFriendsUseCase)
    factoryOf(::GetAllFriendsUseCase)
    factoryOf(::GetAllNotFriendsUseCase)
    factoryOf(::GetAllConversationUseCase)
    factoryOf(::ConvertConversationDTOUseCase)
    factoryOf(::RemoveListenerUseCase)

    factoryOf(::ConversationViewModel)
    factoryOf(::FriendsViewModel)
}