package com.example.ui

import com.example.ui.chat.stateholder.ChatViewModel
import com.example.ui.conversation.stateholder.ConversationViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val uiModule = module {
    singleOf(::ConversationViewModel)
//    singleOf(::FriendsViewModel)
    singleOf(::ChatViewModel)
}