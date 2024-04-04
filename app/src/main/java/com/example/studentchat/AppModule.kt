package com.example.studentchat

import com.example.studentchat.authentication.AuthenticationManager
import com.example.studentchat.authentication.domain.IsLoggedInUseCase
import com.example.studentchat.authentication.domain.LogInWithEmailPasswordUseCase
import com.example.studentchat.authentication.domain.LogOutUseCase
import com.example.studentchat.authentication.domain.SignUpWithEmailPasswordUseCase
import com.example.domain.chat.FormatTimestampUseCase
import com.example.domain.chat.GetAllMessageUseCase
import com.example.domain.chat.GetLastMessageUseCase
import com.example.domain.chat.SendMessageUseCase
import com.example.domain.chat.TimestampToLocalDateUseCase
import com.example.studentchat.chat.ui.stateholder.ChatViewModel
import com.example.domain.conversation.ConvertConversationUseCase
import com.example.domain.conversation.CreateConversationUseCase
import com.example.domain.conversation.DeleteConversationUseCase
import com.example.domain.conversation.GetAllConversationsUseCase
import com.example.studentchat.conversation.ui.stateholder.ConversationViewModel
import com.example.domain.friends.AddFriendsUseCase
import com.example.domain.friends.GetAllFriendsUseCase
import com.example.domain.friends.GetAllNotFriendsUseCase
import com.example.studentchat.friends.ui.stateholder.FriendsViewModel
import com.example.domain.user.GetCurrentUserUseCase
import com.example.domain.user.GetUserUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {

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
    singleOf(::TimestampToLocalDateUseCase)
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