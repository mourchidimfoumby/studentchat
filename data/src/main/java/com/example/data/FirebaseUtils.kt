package com.example.data

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.FirebaseDatabase

private const val URL_DATABASE = "https://studentchat-a99ae-default-rtdb.europe-west1.firebasedatabase.app/"
const val TABLE_USER_CONVERSATION = "user-conversation"
const val TABLE_CONVERSATION = "conversation"
const val TABLE_USER_FRIENDS = "user-friends"
const val TABLE_USER = "user"
const val TABLE_MESSAGE = "message"
val firebaseDatabase = FirebaseDatabase.getInstance(URL_DATABASE).reference
val auth = Firebase.auth
val userId = Firebase.auth.uid ?: run {
    Log.w("Firebase", "userId is null")
    ""
}