package com.example.studenchat.authentication

import androidx.lifecycle.ViewModel
import com.example.studenchat.data.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthenticationViewModel: ViewModel() {

    private val auth = Firebase.auth

    fun createUser(user: User){
        auth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){

                }
            }
    }
}