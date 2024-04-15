package com.example.authentication

import com.example.authentication.domain.IsLoggedInUseCase
import com.example.authentication.domain.LogInWithEmailPasswordUseCase
import com.example.authentication.domain.LogOutUseCase
import com.example.authentication.domain.SignUpWithEmailPasswordUseCase
import com.example.authentication.ui.AuthenticationViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val authenticationModule = module {
    singleOf(::AuthenticationViewModel)
    singleOf(::AuthenticationManager)

    singleOf(::IsLoggedInUseCase)
    singleOf(::LogOutUseCase)
    singleOf(::LogInWithEmailPasswordUseCase)
    singleOf(::SignUpWithEmailPasswordUseCase)
}