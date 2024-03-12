package com.example.studentchat.authentication

import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class AuthenticationRepository {
    private val auth = Firebase.auth

    suspend fun logInWithEmailPassword(mail: String, password: String) = suspendCoroutine { continuation ->
        auth.signInWithEmailAndPassword(mail, password)
            .addOnSuccessListener {
                Log.i(javaClass.name, "Connection successful !")
                continuation.resume(Unit)
            }
            .addOnFailureListener { e ->
                Log.e(javaClass.name, e.message.toString())
                continuation.resumeWithException(e)
            }
    }
    suspend fun signUpWithEmailPassword(mail: String, password: String) = suspendCoroutine { continuation ->
        auth.createUserWithEmailAndPassword(mail, password)
            .addOnSuccessListener {
                Log.i(javaClass.name, "Registration successful !")
                continuation.resume(Unit)
            }
            .addOnFailureListener { e ->
                Log.e(javaClass.name, e.message.toString())
                continuation.resumeWithException(e)
            }
    }
}
