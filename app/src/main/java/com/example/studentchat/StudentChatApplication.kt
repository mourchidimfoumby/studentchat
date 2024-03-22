package com.example.studentchat

import android.app.Application
import android.content.Intent
import com.example.studentchat.authentication.AuthenticationActivity
import com.google.firebase.auth.FirebaseAuth
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

        FirebaseAuth.AuthStateListener {
            if (it.currentUser == null) {
                Intent(this, AuthenticationActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        }
    }
}