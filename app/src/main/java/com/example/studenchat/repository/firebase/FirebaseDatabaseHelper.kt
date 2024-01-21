package com.example.studenchat.repository.firebase

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.suspendCoroutine

object FirebaseDatabaseHelper {
    const val URL_DATABASE = "https://studentchat-a99ae-default-rtdb.europe-west1.firebasedatabase.app/"
    val firebaseDatabase = FirebaseDatabase.getInstance(URL_DATABASE).reference
    val TAG = FirebaseDatabaseHelper.javaClass.name
    fun insert(table: String, data: Any){
        firebaseDatabase.child(table).setValue(data)
            .addOnSuccessListener {
                Log.i(TAG, "Insert success")
            }
            .addOnFailureListener {
                throw Exception(it)
        }
    }
    fun insert(dataToInsert: Map<String, Any>) {
        firebaseDatabase.setValue(dataToInsert)
            .addOnSuccessListener {
                Log.i(TAG, "Insert success")
            }
            .addOnFailureListener {
                throw Exception(it)
            }
    }
}