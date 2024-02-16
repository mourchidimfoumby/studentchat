package com.example.studenchat.domain

import android.util.Log
import com.example.studenchat.data.interfaces.UserRepository
import com.example.studenchat.data.source.User
import com.example.studenchat.utils.FirebaseUtils.Companion.auth
import com.example.studenchat.utils.FirebaseUtils.Companion.firebaseDatabase
import com.example.studenchat.utils.TABLE_USER
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class UserRepositoryImpl: UserRepository{
    suspend fun createUser(user: User) {
        suspendCoroutine { continuation ->
            firebaseDatabase
                .child(TABLE_USER)
                .child(auth.uid!!)
                .setValue(user)
                .addOnSuccessListener {
                    setUidAttribute()
                    continuation.resume(Unit)
                }
                .addOnFailureListener { error ->
                    Log.e(javaClass.name, error.message!!)
                    continuation.resumeWithException(error)
                }
        }
    }
    private fun setUidAttribute() {
        firebaseDatabase
            .child(TABLE_USER)
            .child(auth.uid!!)
            .child("uid")
            .setValue(auth.uid)
            .addOnFailureListener { throw Exception(it) }
    }

    override suspend fun getAllUser(): List<User> {
        TODO("Not yet implemented")
    }

    override suspend fun getUser(uid: String): User {
        TODO("Not yet implemented")
    }

    override suspend fun getCurrentUser(): User {
        TODO("Not yet implemented")
    }

    /*suspend fun getAllUser(): List<User> {
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
        }*/
}

