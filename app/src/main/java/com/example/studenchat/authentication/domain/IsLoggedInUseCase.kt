package com.example.studenchat.authentication.domain

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class IsLoggedInUseCase {
    private val auth = Firebase.auth
    operator fun invoke(): Boolean{
        return auth.currentUser != null
    }
}