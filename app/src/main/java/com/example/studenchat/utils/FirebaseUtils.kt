package com.example.studenchat.utils

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.FirebaseDatabase

const val TABLE_CONVERSATION = "conversation"
const val TABLE_MESSAGE = "message"
const val TABLE_USER = "user"
const val URL_DATABASE = "https://studentchat-a99ae-default-rtdb.europe-west1.firebasedatabase.app/"

class FirebaseUtils {
    companion object {
        val userId = Firebase.auth.uid
        val auth = Firebase.auth
        val firebaseDatabase = FirebaseDatabase.getInstance(URL_DATABASE).reference
    }
}