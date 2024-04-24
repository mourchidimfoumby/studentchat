package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.TABLE_MESSAGE

@Entity(tableName = TABLE_MESSAGE)
internal data class MessageEntity(
    val authorId: String,
    @PrimaryKey val timestamp: Long,
    val text: String,
    val conversationId: String
)
