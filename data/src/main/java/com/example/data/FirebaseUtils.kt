package com.example.data

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.FirebaseDatabase

private const val URL_DATABASE = "https://studentchat-a99ae-default-rtdb.europe-west1.firebasedatabase.app/"
const val TABLE_USER_CONVERSATIONS = "user-conversations"
const val TABLE_CONVERSATIONS = "conversations"
const val TABLE_USER_FRIENDS = "user-friends"
const val TABLE_USERS = "users"
const val TABLE_MESSAGES = "messages"
val firebaseDatabase = FirebaseDatabase.getInstance(URL_DATABASE).reference
val auth = Firebase.auth
val userId = Firebase.auth.uid ?: run {
    Log.w("Firebase", "userId is null")
    ""
}