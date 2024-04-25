package com.example.data

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.FirebaseDatabase

private const val URL_DATABASE =
    "https://studentchat-a99ae-default-rtdb.europe-west1.firebasedatabase.app/"
internal const val TABLE_USER_CONVERSATION = "user_conversation"
internal const val TABLE_CONVERSATION = "conversation"
internal const val TABLE_USER_FRIENDS = "user_friends"
internal const val TABLE_USER = "user"
internal const val TABLE_MESSAGE = "message"
internal val firebaseDatabase = FirebaseDatabase.getInstance(URL_DATABASE).reference
internal val auth = Firebase.auth
internal val userId = Firebase.auth.uid ?: run {
    Log.w("Firebase", "userId is null")
    ""
}