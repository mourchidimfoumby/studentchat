package com.example.domain

import com.example.domain.chat.FormatTimestampUseCase
import com.example.domain.chat.TimestampToLocalDateUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainModule = module {
    singleOf(::TimestampToLocalDateUseCase)
    singleOf(::FormatTimestampUseCase)
}