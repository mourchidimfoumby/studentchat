package com.example.studenchat.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.studenchat.repository.FirebaseParameters
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.example.studenchat.model.User

class FriendsViewModel : ViewModel(), FirebaseParameters {
    private val userId = auth.uid
    val TAG: String = FriendsViewModel::class.java.name
    private val _friends = MutableLiveData<List<User>>()
    val friends: LiveData<List<User>> = _friends
    override val tableName = "user-friends"

    init {
        try {
            getAllFriends()
        }catch (e: Exception){
            Log.i(TAG, "Pas d'utilisateur présent")
        }
    }
    private fun getAllFriends() {
        firebaseDatabase.child("users").orderByValue().addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val friendsList = mutableListOf<User>()
                for (result in snapshot.children){
                    val friend = result.getValue(User::class.java)
                    friend?.let { friendsList.add(it)}
                }
                _friends.value = friendsList
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e(TAG, error.message)
            }
        })
    }

    fun addFriend(user: User) {
        firebaseDatabase.child(tableName).child(userId!!).setValue(user).addOnFailureListener {error ->
            Log.e(TAG, error.message!!)
        }
    }

    fun removeFriend(friendId: String) {
        firebaseDatabase.child(tableName).child(userId!!).child(friendId).setValue(null).addOnFailureListener {error ->
            Log.e(TAG, error.message!!)
        }
    }
}