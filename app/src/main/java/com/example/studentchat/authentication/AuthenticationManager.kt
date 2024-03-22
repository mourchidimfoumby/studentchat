package com.example.studentchat.authentication

import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthenticationManager {
    private val auth = Firebase.auth

    fun logInWithEmailPassword(mail: String, password: String) {
        auth.signInWithEmailAndPassword(mail, password)
            .addOnSuccessListener {
                Log.i(javaClass.name, "Connected")
            }
            .addOnFailureListener { e ->
                throw Exception(e)
            }
    }

    fun signUpWithEmailPassword(mail: String, password: String) {
        auth.createUserWithEmailAndPassword(mail, password)
            .addOnSuccessListener {
                Log.i(javaClass.name, "Registered")
            }
            .addOnFailureListener { e ->
                throw Exception(e)
            }
    }
}
