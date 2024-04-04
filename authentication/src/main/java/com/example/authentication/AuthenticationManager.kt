package com.example.authentication

import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthenticationManager {
    private val auth = Firebase.auth

    fun logInWithEmailPassword(
        mail: String,
        password: String,
        callback: (Result<AuthenticationState>) -> Unit
    ) {
        auth.signInWithEmailAndPassword(mail, password)
            .addOnSuccessListener {
                callback(Result.success(AuthenticationState.AUTHENTICATED))
                Log.i(javaClass.name, "Connected")
            }
            .addOnFailureListener { e ->
                callback(Result.failure(e))
            }
    }

    fun signUpWithEmailPassword(
        mail: String,
        password: String,
        callback: (Result<AuthenticationState>) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(mail, password)
            .addOnSuccessListener {
                callback(Result.success(AuthenticationState.AUTHENTICATED))
                Log.i(javaClass.name, "Registered")
            }
            .addOnFailureListener { e ->
                callback(Result.failure(e))
            }
    }
}
