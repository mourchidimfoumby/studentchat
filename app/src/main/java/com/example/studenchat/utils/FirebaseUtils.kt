package com.example.studenchat.utils

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import kotlin.reflect.KClass

<<<<<<< Updated upstream
const val TABLE_CONVERSATION = "conversation"
const val TABLE_MESSAGE = "message"
const val TABLE_USER = "user"
const val URL_DATABASE = "https://studentchat-a99ae-default-rtdb.europe-west1.firebasedatabase.app/"

class FirebaseUtils {
    companion object {
        val userId = Firebase.auth.uid
        val auth = Firebase.auth
        val firebaseDatabase = FirebaseDatabase.getInstance(URL_DATABASE).reference
=======
private const val URL_DATABASE = "https://studentchat-a99ae-default-rtdb.europe-west1.firebasedatabase.app/"
const val TABLE_USER_CONVERSATIONS = "user-conversations"
const val TABLE_CONVERSATIONS = "conversations"
const val TABLE_USER_FRIENDS = "user-friends"
const val TABLE_USERS = "users"
val firebaseDatabase = FirebaseDatabase.getInstance(URL_DATABASE).reference
val auth = Firebase.auth
val userId = Firebase.auth.uid ?: run {
    Log.w("Firebase", "userId is null")
    ""
}

suspend fun DatabaseReference.remove(){ suspendCoroutine { continuation ->
    try {
        setValue(null)
            .addOnSuccessListener {
                continuation.resume(Unit)
            }
            .addOnFailureListener { error ->
                continuation.resumeWithException(error)
            }
    } catch (e: Exception){
        Log.e(javaClass.name, "Failed to remove value at $root", e)
    }
    }
}

suspend fun DatabaseReference.set(value: Any){ suspendCoroutine { continuation ->
    try {
        setValue(value)
            .addOnSuccessListener {
                continuation.resume(Unit)
            }
            .addOnFailureListener { error ->
                continuation.resumeWithException(error)
            }
        } catch (e: Exception){
        Log.e(javaClass.name, "Failed to add $value at $root", e)
        }
    }
}

suspend fun <T> DatabaseReference.getValue(type: Class<T>): T? = suspendCoroutine { continuation ->
    get().addOnSuccessListener { snapshot ->
        val value = snapshot.getValue(type)
        continuation.resume(value)
    }.addOnFailureListener { error ->
        Log.e(javaClass.name, "Failed to get the value at $root", error)
        continuation.resume(null)
>>>>>>> Stashed changes
    }
}