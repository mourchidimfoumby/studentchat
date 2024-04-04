package com.example.authentication

import com.example.authentication.domain.IsLoggedInUseCase
import com.example.authentication.domain.LogInWithEmailPasswordUseCase
import com.example.authentication.domain.LogOutUseCase
import com.example.authentication.domain.SignUpWithEmailPasswordUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val authenticationModule = module {
    singleOf(::IsLoggedInUseCase)
    singleOf(::LogOutUseCase)
    singleOf(::LogInWithEmailPasswordUseCase)
    singleOf(::SignUpWithEmailPasswordUseCase)
    singleOf(::AuthenticationManager)
}