package com.example.studenchat.firebase

import android.util.Log
import com.example.studenchat.data.UserEmailPassword
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

object FirebaseAuthenticationHelper {
    private val auth = Firebase.auth
    private val TAG = FirebaseAuthenticationHelper::class.java.name

    fun signInWithEmailAndPassword(user: UserEmailPassword){
        auth.signInWithEmailAndPassword(user.mail, user.password)
            .addOnSuccessListener {
                Log.i(TAG, "Connection successful")
                return@addOnSuccessListener
            }
            .addOnFailureListener { e->
                Log.e(TAG, e.message.toString())
                throw Exception(e.message)
            }
    }

    fun userIsConnected(): Boolean{
        return auth.currentUser != null
    }
}