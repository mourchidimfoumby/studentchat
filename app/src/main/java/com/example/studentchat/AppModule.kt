package com.example.studentchat

import com.example.domain.chat.FormatTimestampUseCase
import com.example.domain.chat.TimestampToLocalDateUseCase
import com.example.studentchat.chat.ui.stateholder.ChatViewModel
import com.example.studentchat.conversation.ui.stateholder.ConversationViewModel
import com.example.studentchat.friends.ui.stateholder.FriendsViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {

    singleOf(::RemoveListenerUseCase)
    singleOf(::TimestampToLocalDateUseCase)
    singleOf(::FormatTimestampUseCase)
    singleOf(::RemoveListenerUseCase)
    singleOf(::ConversationViewModel)
    singleOf(::FriendsViewModel)
    singleOf(::ChatViewModel)
}