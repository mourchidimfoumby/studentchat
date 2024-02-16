package com.example.studenchat

import android.app.Application
import com.example.studenchat.injection.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class StudentChatApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@StudentChatApplication)
            modules(appModule)
        }
    }
}