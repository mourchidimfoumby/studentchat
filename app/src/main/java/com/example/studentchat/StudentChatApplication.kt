package com.example.studentchat

import android.app.Application
import com.example.authentication.authenticationModule
import com.example.data.dataModule
import com.example.domain.domainModule
import com.example.ui.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class StudentChatApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@StudentChatApplication)
            modules(
                authenticationModule,
                dataModule,
                domainModule,
                uiModule
            )
        }
    }
}