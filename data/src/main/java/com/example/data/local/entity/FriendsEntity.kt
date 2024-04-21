package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.TABLE_USER_FRIENDS

@Entity(tableName = TABLE_USER_FRIENDS)
internal data class FriendsEntity(
    @PrimaryKey
    val uid: String,
    val firstName: String,
    val lastName: String,
    val mail: String,
)