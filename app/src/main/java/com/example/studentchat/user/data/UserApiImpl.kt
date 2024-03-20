package com.example.studentchat.user.data

import com.example.studentchat.FirebaseApi
import com.example.studentchat.utils.TABLE_USERS
import com.example.studentchat.utils.firebaseDatabase
import com.example.studentchat.utils.userId
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class UserApiImpl : UserApi, FirebaseApi {
    private var allUserValueEventListener: ValueEventListener? = null
    private var currentUserValueEventListener: ValueEventListener? = null
    private val userDatabaseReference = firebaseDatabase.child(TABLE_USERS)
    override fun getAllUser(): Flow<List<User>> = callbackFlow {
        allUserValueEventListener =
            userDatabaseReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val userList = snapshot.children.mapNotNull { it.getValue(User::class.java) }
                    trySend(userList)
                }

                override fun onCancelled(error: DatabaseError) {
                    close(error.toException())
                }
            })
        awaitClose()
    }

    override fun getCurrentUser(): Flow<User> = callbackFlow {
        currentUserValueEventListener =
            userDatabaseReference.child(userId).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val currentUser = snapshot.getValue(User::class.java)
                    currentUser?.let {
                        trySend(it)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    close(error.toException())
                }
            })
        awaitClose()
    }

    override suspend fun getUser(uid: String): User? {
        return userDatabaseReference.child(uid)
            .get()
            .await()
            .getValue(User::class.java)
    }

    override suspend fun insertUser(user: User) {
        userDatabaseReference.child(user.uid).setValue(user)
    }

    override suspend fun updateUser(user: User) {
        userDatabaseReference.child(user.uid).setValue(user)
    }

    override suspend fun deleteCurrentUser() {
        userDatabaseReference.child(userId).removeValue()
    }

    override fun removeListener() {
        allUserValueEventListener?.let {
            userDatabaseReference.removeEventListener(it)
        }
        currentUserValueEventListener?.let {
            userDatabaseReference.removeEventListener(it)
        }
    }

}