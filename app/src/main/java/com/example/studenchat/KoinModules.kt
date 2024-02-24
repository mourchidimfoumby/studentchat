package com.example.studenchat

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.example.studenchat.data.repository.UserConversationRepository
import com.example.studenchat.domain.FriendsRepositoryImpl
import com.example.studenchat.data.repository.FriendsRepository
import com.example.studenchat.data.repository.UserRepository
import com.example.studenchat.domain.ConversationRepository
import com.example.studenchat.domain.UserConversationRepositoryImpl
import com.example.studenchat.domain.UserRepositoryImpl
import com.example.studenchat.domain.usecase.CreateConversationUseCase
import com.example.studenchat.domain.usecase.DeleteConversationUseCase
import com.example.studenchat.domain.usecase.AddFriendsUseCase
import com.example.studenchat.domain.usecase.ConvertConversationDTOUseCase
import com.example.studenchat.domain.usecase.GetAllConversationUseCase
import com.example.studenchat.domain.usecase.GetAllFriendsUseCase
import com.example.studenchat.domain.usecase.GetCurrentUserUseCase
import com.example.studenchat.domain.usecase.GetUserWithUidUseCase
import com.example.studenchat.ui.conversation.ConversationViewModel
import com.example.studenchat.ui.friends.FriendsViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf

val appModule = module {
    singleOf(::UserConversationRepositoryImpl) { bind<UserConversationRepository>() }
    singleOf(::UserRepositoryImpl) { bind<UserRepository>() }
    singleOf(::FriendsRepositoryImpl) { bind<FriendsRepository>() }
    factoryOf(::CreateConversationUseCase)
    factoryOf(::DeleteConversationUseCase)
    factoryOf(::GetUserWithUidUseCase)
    factoryOf(::GetCurrentUserUseCase)
    factoryOf(::AddFriendsUseCase)
    factoryOf(::GetAllFriendsUseCase)
    factoryOf(::GetAllConversationUseCase)
    factoryOf(::ConvertConversationDTOUseCase)
    factoryOf(::ConversationViewModel)
    factoryOf(::FriendsViewModel)
    factoryOf(::ConversationRepository)
}