package com.example.studentchat.friends.data

import android.util.Log
import com.example.studentchat.user.data.User
import com.example.studentchat.utils.TABLE_USER_FRIENDS
import com.example.studentchat.utils.firebaseDatabase
import com.example.studentchat.utils.userId
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class FriendsRepositoryImpl: FriendsRepository {

    private val friendsDatabaseReference =
        firebaseDatabase.child(TABLE_USER_FRIENDS)
    private var valueEventListener: ValueEventListener? = null

    override suspend fun getAllFriendsUid(): Flow<List<String>?> = callbackFlow {
        valueEventListener = friendsDatabaseReference
            .child(userId)
            .addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val friendsUid = snapshot.children.map { it.key!! }.toList()
                    trySend(friendsUid.ifEmpty { null })
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e(javaClass.name, "Failed to get values", error.toException())
                }

            })
        awaitClose {}
    }

    override suspend fun addFriend(user: User) {
        friendsDatabaseReference.setValue(user.uid)
            .addOnFailureListener { error ->
                Log.e(javaClass.name, "Failed to insert value.", error)
            }
    }
    override suspend fun removeFriend(friendId: String) {
        friendsDatabaseReference.setValue(null)
            .addOnFailureListener { error ->
                Log.e(javaClass.name, "Failed to delete value.", error)
            }
    }

    fun removeListener() {
        valueEventListener?.let {
            friendsDatabaseReference.removeEventListener(it)
        }
    }
}