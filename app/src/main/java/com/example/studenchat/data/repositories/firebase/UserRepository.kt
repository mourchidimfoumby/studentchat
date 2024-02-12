package com.example.studenchat.data.repositories.firebase

import android.util.Log
import com.example.studenchat.data.sources.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class UserRepository : FirebaseParameters {
    override val tableName: String = "users"
    suspend fun getAllUser(): List<User> {
        return suspendCoroutine { continuation ->
            firebaseDatabase.child(tableName).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val userList = snapshot.children.mapNotNull { it.getValue(User::class.java) }
                    continuation.resume(userList)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e(javaClass.name, error.message)
                }
            })
        }
    }

    suspend fun getUser(user: User) = suspendCoroutine { continuation ->
        firebaseDatabase.child(tableName).child(user.uid)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    continuation.resume(snapshot.getValue(User::class.java))
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e(javaClass.name, error.message)
                }

            })
    }


    suspend fun getUser(uid: String) = suspendCoroutine { continuation ->
        firebaseDatabase.child(tableName).child(uid)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    continuation.resume(snapshot.getValue(User::class.java))
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e(javaClass.name, error.message)
                }

            })
    }


    suspend fun removeUser(user: User) {
        suspendCoroutine<Unit> {
            firebaseDatabase.child(tableName).child(user.uid).setValue(null)
        }
    }

    suspend fun addUser(user: User) {
        suspendCoroutine { continuation ->
            firebaseDatabase.child(tableName).setValue(user)
                .addOnSuccessListener { continuation.resume(Unit) }
                .addOnFailureListener { error ->
                    Log.e(javaClass.name, error.message!!)
                }
        }
    }

    suspend fun getCurrentUser(): User =
        suspendCoroutine { continuation ->
            firebaseDatabase.child(tableName).child(userId!!)
                .addListenerForSingleValueEvent(object :
                    ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val user = snapshot.getValue(User::class.java)
                        continuation.resume(user!!)
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.e(javaClass.name, error.message)
                    }
                })
        }

    suspend fun createUser(user: User) = suspendCoroutine { continuation ->
        firebaseDatabase.child(tableName).child(auth.uid!!).setValue(user)
        .addOnSuccessListener {
            setUidAttribute()
            continuation.resume(Unit)
        }
        .addOnFailureListener { error ->
            Log.e(javaClass.name, error.message!!)
            continuation.resumeWithException(error)
        }
    }
    private fun setUidAttribute() = firebaseDatabase
        .child(tableName)
        .child(auth.uid!!)
        .child("uid")
        .setValue(auth.uid)
        .addOnFailureListener { throw Exception(it) }
    }

}
