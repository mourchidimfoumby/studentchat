package com.example.ui

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val uiModule = module {
    singleOf(::ConversationViewModel)
    singleOf(::FriendsViewModel)
    singleOf(::ChatViewModel)
}