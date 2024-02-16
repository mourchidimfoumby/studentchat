package com.example.studenchat.injection

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.example.studenchat.data.interfaces.ConversationRepository
import com.example.studenchat.data.interfaces.UserRepository
import com.example.studenchat.domain.ConversationRepositoryImpl
import com.example.studenchat.domain.UserRepositoryImpl
import com.example.studenchat.domain.usecase.CreateConversationUseCase
import com.example.studenchat.domain.usecase.DeleteConversationUseCase
import com.example.studenchat.domain.usecase.GetAllConversationUseCase
import com.example.studenchat.stateholder.viewmodel.ConversationViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf

val appModule = module {
    singleOf(::ConversationRepositoryImpl) { bind<ConversationRepository>() }
    singleOf(::UserRepositoryImpl) { bind<UserRepository>() }
    factoryOf(::CreateConversationUseCase)
    factoryOf(::DeleteConversationUseCase)
    factoryOf(::GetAllConversationUseCase)
    factoryOf(::ConversationViewModel)
}