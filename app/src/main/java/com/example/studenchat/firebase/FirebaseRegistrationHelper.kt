package com.example.studenchat.firebase

import android.util.Log
import com.example.studenchat.data.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

object FirebaseRegistrationHelper {
    private val auth = Firebase.auth
    private val TAG = FirebaseRegistrationHelper::class.java.name

    fun registerUserWithEmailAndPassword(user: User) {
        auth.createUserWithEmailAndPassword(user.mail, user.password)
            .addOnSuccessListener {
                Log.i(TAG, "Registration successful")
                return@addOnSuccessListener
            }
            .addOnFailureListener { e ->
                Log.e(TAG, e.message.toString())
                throw Exception(e.message)
            }
    }
}