package com.example.data.remote.api

import com.example.data.TABLE_USER
import com.example.data.firebaseDatabase
import com.example.data.remote.model.UserRemote
import com.example.data.userId
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

internal class UserApiImpl : UserApi {
    private val userDatabaseReference = firebaseDatabase.child(TABLE_USER)
    override fun getAllUser(): Flow<List<UserRemote>> = callbackFlow {
        userDatabaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userList = snapshot.children.mapNotNull { it.getValue(UserRemote::class.java) }
                userList.filterNot { it.uid == userId }
                trySend(userList)
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        })
        awaitClose()
    }

    override fun getCurrentUser(): Flow<UserRemote> = callbackFlow {
        userDatabaseReference.child(userId)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val currentUser = snapshot.getValue(UserRemote::class.java)
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

    override suspend fun getUser(uid: String): UserRemote? =
        userDatabaseReference.child(uid)
            .get()
            .await()
            .getValue(UserRemote::class.java)

    override suspend fun insertUser(userApiModel: UserRemote) {
        userDatabaseReference.child(userApiModel.uid).setValue(userApiModel)
    }

    override suspend fun updateUser(userApiModel: UserRemote) {
        userDatabaseReference.child(userApiModel.uid).setValue(userApiModel)
    }

    override suspend fun deleteCurrentUser() {
        userDatabaseReference.child(userId).removeValue()
    }

}