package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.TABLE_CONVERSATION

@Entity(tableName = TABLE_CONVERSATION)
internal data class ConversationEntity(
    @PrimaryKey
    val id: String,
    val interlocutorId: String,
    var lastMessageTimestamp: Long,
)