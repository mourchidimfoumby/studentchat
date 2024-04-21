package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.TABLE_MESSAGE

@Entity(tableName = TABLE_MESSAGE)
internal data class MessageEntity(
    @PrimaryKey
    val timestamp: Long,
    val authorId: String,
    val text: String,
    val conversationId: String
)
