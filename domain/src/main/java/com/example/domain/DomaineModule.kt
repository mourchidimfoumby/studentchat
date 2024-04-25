package com.example.domain

import com.example.domain.chat.FormatTimestampUseCase
import com.example.domain.chat.GetAllMessageUseCase
import com.example.domain.chat.GetLastMessageUseCase
import com.example.domain.chat.SendMessageUseCase
import com.example.domain.chat.TimestampToLocalDateUseCase
import com.example.domain.conversation.CreateConversationUseCase
import com.example.domain.conversation.DeleteConversationUseCase
import com.example.domain.conversation.GetAllConversationsUseCase
import com.example.domain.friends.AddFriendsUseCase
import com.example.domain.friends.GetAllFriendsUseCase
import com.example.domain.friends.GetAllNotFriendsUseCase
import com.example.domain.user.GetCurrentUserUseCase
import com.example.domain.user.GetUserUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainModule = module {

    singleOf(::GetAllConversationsUseCase)
    singleOf(::CreateConversationUseCase)
    singleOf(::DeleteConversationUseCase)

    singleOf(::GetUserUseCase)
    singleOf(::GetCurrentUserUseCase)

    singleOf(::AddFriendsUseCase)
    singleOf(::GetAllFriendsUseCase)
    singleOf(::GetAllNotFriendsUseCase)

    singleOf(::SendMessageUseCase)
    singleOf(::GetAllMessageUseCase)
    singleOf(::GetLastMessageUseCase)

    singleOf(::TimestampToLocalDateUseCase)
    singleOf(::FormatTimestampUseCase)
}