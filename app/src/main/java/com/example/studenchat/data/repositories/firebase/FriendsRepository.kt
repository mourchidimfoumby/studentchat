package com.example.studenchat.data.repositories.firebase

import android.util.Log
import com.example.studenchat.data.sources.User
import com.example.studenchat.domain.user.GetUserWithUID
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FriendsRepository : FirebaseParameters {
    override val tableName: String = "user-friends"
    private suspend fun getAllUidFriends(): List<String> = suspendCoroutine { continuation ->
        val friendsUidList = mutableListOf<String>()
        firebaseDatabase.child(tableName).child(userId!!).addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.childrenCount > 0) {
                    snapshot.children.forEach {
                        val friendUID = it.getValue(String::class.java)
                        friendsUidList.add(friendUID!!)
                    }
                } else {
                    val friendUID = snapshot.getValue(String::class.java)
                    if(friendUID != null)
                        friendsUidList.add(friendUID)
                }
                continuation.resume(friendsUidList)
            }
            override fun onCancelled(error: DatabaseError) {
                Log.e(javaClass.name, error.message)
            }
        })
    }

    suspend fun getAllFriends(): List<User> {
        val friendUidList = getAllUidFriends()
        val friendsList = mutableListOf<User>()
        friendUidList.forEach {
            val friend = GetUserWithUID(UserRepository()).invoke(it)
            friendsList.add(friend!!)
        }
        return friendsList
    }

    /*suspend fun getAllUidNotFriends(): List<User> {

    }*/
   suspend fun addFriend(user: User) {
       suspendCoroutine<Unit> {
           firebaseDatabase.child(tableName).child(userId!!).setValue(user.uid)
            .addOnFailureListener { error ->
                Log.e(javaClass.name, error.message!!)
            }
        }
    }

    suspend fun removeFriend(friendId: String) {
        suspendCoroutine<Unit> {
            firebaseDatabase.child(tableName).child(userId!!).child(friendId).setValue(null)
                .addOnFailureListener { error ->
                    Log.e(javaClass.name, error.message!!)
                }
        }
    }
}