package com.example.studentchat.friends.data

import com.example.studentchat.FirebaseApi
import com.example.studentchat.user.data.User
import com.example.studentchat.user.data.UserApi
import com.example.studentchat.utils.TABLE_USER_FRIENDS
import com.example.studentchat.utils.firebaseDatabase
import com.example.studentchat.utils.userId
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.tasks.await

class FriendsApiImpl(private val userApi: UserApi) : FriendsApi, FirebaseApi {
    private val friendsDatabaseReference = firebaseDatabase.child(TABLE_USER_FRIENDS)
    private var allFriendsEventListener: ValueEventListener? = null
    override fun getAllFriends(): Flow<List<Friends>> = callbackFlow {
        allFriendsEventListener = friendsDatabaseReference.child(userId)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val friendsList =
                        snapshot.children.mapNotNull { it.getValue(Friends::class.java) }
                    trySend(friendsList)
                }

                override fun onCancelled(error: DatabaseError) {
                    close(error.toException())
                }
            })
        awaitClose()
    }

    override fun getAllNotFriends(): Flow<List<User>> = callbackFlow {
        val friendsList = getAllFriends().first()
        val userList = userApi.getAllUser().first()
        val notFriends = userList.filterNot { user ->
            friendsList.any { friends -> friends.uid == user.uid }
        }
        trySend(notFriends)
        awaitClose()
    }

    override suspend fun getFriends(uid: String): Friends? =
        friendsDatabaseReference.child(userId)
            .child(uid)
            .get()
            .await()
            .getValue(Friends::class.java)


    override suspend fun insertFriends(user: User) {
        firebaseDatabase.child(userId)
            .setValue(user.uid)
    }

    override suspend fun deleteFriends(friends: Friends) {
        friendsDatabaseReference.child(userId)
            .child(friends.uid)
            .removeValue()
    }

    override fun removeListener() {
        allFriendsEventListener?.let {
            friendsDatabaseReference.removeEventListener(it)
        }
    }
}