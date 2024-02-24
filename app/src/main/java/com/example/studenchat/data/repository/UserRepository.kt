package com.example.studenchat.data.repository

import com.example.studenchat.data.source.User

interface UserRepository: Repository {
    suspend fun getAllUser(): List<User>
    suspend fun getUser(uid: String): User?
    suspend fun getCurrentUser(): User?
    suspend fun createUser(user: User)
    suspend fun removeCurrentUser()
}