package com.example.data.remote.api

import android.util.Log
import com.example.data.TABLE_USER_FRIENDS
import com.example.data.firebaseDatabase
import com.example.data.remote.model.FriendsRemote
import com.example.data.remote.model.UserRemote
import com.example.data.userId
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

internal class FriendsApiImpl(private val userApi: UserApi) : FriendsApi {
    private val friendsDatabaseReference = firebaseDatabase.child(TABLE_USER_FRIENDS)
    override fun getAllFriends(): Flow<List<FriendsRemote>> = callbackFlow {
        friendsDatabaseReference.child(userId)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val friendsIdsList = snapshot.children.mapNotNull { it.key }
                    val jobs = friendsIdsList.map { friendsId ->
                        async {
                            val user = userApi.getUser(friendsId)
                            user?.let {
                                FriendsRemote(
                                    uid = it.uid,
                                    lastName = it.lastName,
                                    firstName = it.firstName,
                                    mail = it.mail
                                )
                            }
                        }
                    }
                    CoroutineScope(Dispatchers.IO).launch {
                        trySend(jobs.mapNotNull { it.await() })
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e(javaClass.name, "Error getting friends", error.toException())
                    close()
                }
            })
        awaitClose()
    }

    override fun getAllNotFriends(): Flow<List<UserRemote>> = callbackFlow {
        val friendsList = getAllFriends().first()
        val userList = userApi.getAllUser().first()
        val notFriends = userList.filterNot { user ->
            friendsList.any { friends -> friends.uid == user.uid }
        }
        trySend(notFriends)
        awaitClose()
    }

    override suspend fun getFriends(uid: String): FriendsRemote? =
        friendsDatabaseReference.child(userId)
            .child(uid)
            .get()
            .await()
            .getValue(FriendsRemote::class.java)


    override suspend fun insertFriends(friendsRemote: FriendsRemote) {
        firebaseDatabase.child(userId)
            .setValue(friendsRemote.uid)
    }

    override suspend fun deleteFriends(friendsRemote: FriendsRemote) {
        friendsDatabaseReference.child(userId)
            .child(friendsRemote.uid)
            .removeValue()
    }
}