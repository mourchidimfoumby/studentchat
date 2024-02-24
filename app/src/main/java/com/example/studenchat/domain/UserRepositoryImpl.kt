package com.example.studenchat.domain

import android.util.Log
<<<<<<< Updated upstream
import com.example.studenchat.data.interfaces.UserRepository
import com.example.studenchat.data.source.User
import com.example.studenchat.utils.FirebaseUtils.Companion.auth
import com.example.studenchat.utils.FirebaseUtils.Companion.firebaseDatabase
import com.example.studenchat.utils.TABLE_USER
=======
import com.example.studenchat.data.repository.UserRepository
import com.example.studenchat.data.source.User
import com.example.studenchat.utils.TABLE_USERS
import com.example.studenchat.utils.auth
import com.example.studenchat.utils.firebaseDatabase
import com.example.studenchat.utils.getValue
import com.example.studenchat.utils.remove
import com.example.studenchat.utils.set
import com.example.studenchat.utils.userId
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.tasks.await
>>>>>>> Stashed changes
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

<<<<<<< Updated upstream
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
=======
class UserRepositoryImpl: UserRepository {
    private val userDatabaseReference = firebaseDatabase.child(TABLE_USERS)
    private var valueEventListener: ValueEventListener? = null
    override suspend fun createUser(user: User) {
        userDatabaseReference.child(userId).set(user)
    }

    override suspend fun getAllUser(): List<User> {
        val snapshot = userDatabaseReference.child(userId).get().result
        val userList = mutableListOf<User>()
        snapshot.children.forEach { value ->
            value.getValue(User::class.java)?.let {  user ->
                user.uid = value.key!!
                userList.add(user)
            }
        }
        return userList
    }

    override suspend fun getUser(uid: String): User? {
        val user = userDatabaseReference.child(uid).getValue(User::class.java)
        user?.uid = uid
        return user
    }

    override suspend fun getCurrentUser(): User? {
        val user = userDatabaseReference.child(userId).getValue(User::class.java)
        user?.uid = userId
        return user
    }

    override suspend fun removeCurrentUser() {
        firebaseDatabase.child(TABLE_USERS).child(userId).remove()
    }

    override fun closeListener() {
        valueEventListener?.let {
            userDatabaseReference.removeEventListener(it)
        }
    }
>>>>>>> Stashed changes
}

