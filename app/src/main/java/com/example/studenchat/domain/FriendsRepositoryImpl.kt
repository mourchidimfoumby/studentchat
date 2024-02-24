package com.example.studenchat.domain

import android.util.Log
import com.example.studenchat.data.repository.FriendsRepository
import com.example.studenchat.data.source.User
import com.example.studenchat.utils.TABLE_USER_FRIENDS
import com.example.studenchat.utils.firebaseDatabase
import com.example.studenchat.utils.userId
import com.google.firebase.database.ValueEventListener

class FriendsRepositoryImpl: FriendsRepository {
    private val friendsDatabaseReference =
        firebaseDatabase.child(TABLE_USER_FRIENDS).child(userId)
    private var valueEventListener: ValueEventListener? = null
    override suspend fun getAllFriendsUid(): List<String>{
        val friendsUids = mutableListOf<String>()
        val snapshot = friendsDatabaseReference
            .child(userId)
            .get()
            .result
        snapshot.children.forEach { value ->
            value.key?.let { friendsUids.add(it) }
        }
        return friendsUids
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

    override fun closeListener() {
        valueEventListener?.let {
            friendsDatabaseReference.removeEventListener(it)
        }
    }
}