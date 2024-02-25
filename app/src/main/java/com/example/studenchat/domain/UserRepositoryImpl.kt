package com.example.studenchat.domain

import com.example.studenchat.data.repository.UserRepository
import com.example.studenchat.data.source.User
import com.example.studenchat.utils.TABLE_USERS
import com.example.studenchat.utils.firebaseDatabase
import com.example.studenchat.utils.getValue
import com.example.studenchat.utils.remove
import com.example.studenchat.utils.set
import com.example.studenchat.utils.userId
import com.google.firebase.database.ValueEventListener


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
        userDatabaseReference.child(userId).remove()
    }

    override fun closeListener() {
        valueEventListener?.let {
            userDatabaseReference.removeEventListener(it)
        }
    }
}

