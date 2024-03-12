package com.example.studentchat.authentication.domain

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LogOutUseCase {
    private val auth = Firebase.auth
    operator fun invoke(){
        auth.signOut()
    }
}