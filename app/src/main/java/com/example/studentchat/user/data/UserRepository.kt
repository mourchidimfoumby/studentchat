package com.example.studentchat.user.data

import com.example.studentchat.Repository

interface UserRepository: Repository {
    suspend fun getAllUser(): List<User>?
    suspend fun getUser(uid: String): User?
    suspend fun getUserList(uids: List<String>): List<User>?
    suspend fun getCurrentUser(): User?
    suspend fun createUser(user: User)
    suspend fun removeCurrentUser()
}