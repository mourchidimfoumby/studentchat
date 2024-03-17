package com.example.studentchat.user.data

import android.util.Log
import com.example.studentchat.utils.TABLE_USERS
import com.example.studentchat.utils.firebaseDatabase
import com.example.studentchat.utils.getValue
import com.example.studentchat.utils.userId
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class UserRepositoryImpl: UserRepository {
    private val userDatabaseReference = firebaseDatabase.child(TABLE_USERS)
    private var valueEventListener: ValueEventListener? = null

    override suspend fun createUser(user: User) {
        userDatabaseReference.child(userId).setValue(user).addOnFailureListener {
            Log.e(javaClass.name, "Failed to create user : $user", it)
        }
    }

    override suspend fun getAllUser(): List<User>? = suspendCoroutine { continuation ->
        userDatabaseReference.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val userList = mutableListOf<User>()
                snapshot.children.forEach { dataSnapchot ->
                    val user = dataSnapchot.getValue(User::class.java)
                    user?.let {
                        user.uid = it.uid
                        userList.add(user)
                    }
                }
                continuation.resume(userList.ifEmpty {
                    Log.e(javaClass.name, "Get all users is null")
                    null
                })
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e(javaClass.name, "Failed to get all users", error.toException())
            }
        })
    }

    override suspend fun getUser(uid: String): User? {
        return try {
            val user = userDatabaseReference.child(uid).getValue(User::class.java)
            user?.let{
                it.uid = uid
                return user
            }
        }
        catch (e: Exception){
            Log.e(javaClass.name, "Failed to get user: $uid")
            null
        }
    }

    override suspend fun getUserList(uids: List<String>): List<User>? {
        val userList = mutableListOf<User>()
        uids.forEach { uid ->
            val user = getUser(uid)
            user?.let { userList.add(it) }
        }
        return if(userList.isNotEmpty()) userList
        else null
    }
    
    override suspend fun getCurrentUser(): User? {
        val user = getUser(userId)
        user?.let{
            it.uid = userId
            return user
        }?: run {
            Log.e(javaClass.name, "Current user is null")
            return null
        }
    }

    override suspend fun removeCurrentUser() {
        userDatabaseReference.child(userId).removeValue().addOnFailureListener {
            Log.e(javaClass.name, "Failed to remove current user", it)
        }
    }

    fun removeListener() {
        valueEventListener?.let {
            userDatabaseReference.removeEventListener(it)
        }
    }
}

