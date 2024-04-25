package com.example.studentchat

import com.example.studentchat.sharedpreferences.SharedPreferencesUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    single {
        params -> SharedPreferencesUseCase(get(), params.get())
    }
    singleOf(::AuthenticationListenerUseCase)
}