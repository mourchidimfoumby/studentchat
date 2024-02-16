package com.example.studenchat.data.interfaces

import com.example.studenchat.data.source.User

interface UserRepository {
    suspend fun getAllUser(): List<User>
    suspend fun getUser(uid: String): User
    suspend fun getCurrentUser(): User
}