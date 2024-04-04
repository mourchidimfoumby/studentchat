package com.example.authentication.domain

import android.content.Context
import android.content.Intent
import com.example.authentication.ui.AuthenticationActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LogOutUseCase(private val context: Context) {
    private val auth = Firebase.auth
    operator fun invoke() {
        auth.signOut()
        Intent(context, AuthenticationActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(it)
        }
    }
}