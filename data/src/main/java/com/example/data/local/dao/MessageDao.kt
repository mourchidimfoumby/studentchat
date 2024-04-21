package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.data.TABLE_MESSAGE
import com.example.data.local.entity.MessageEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface MessageDao {
    @Upsert
    fun insertMessage(messageEntity: MessageEntity)

    @Query("SELECT * FROM $TABLE_MESSAGE WHERE conversationId = :conversationId")
    fun getAllMessage(conversationId: String): Flow<MessageEntity>

    @Query(
        """
            SELECT * FROM $TABLE_MESSAGE
            WHERE conversationId = :conversationId
            ORDER BY timestamp DESC
            LIMIT 1
            """
    )
    fun getLastMessage(conversationId: String): MessageEntity?

    @Query("SELECT * FROM $TABLE_MESSAGE WHERE timestamp = :timestamp")
    fun getMessage(timestamp: Long): MessageEntity?

    @Delete
    fun deleteMessage(messageEntity: MessageEntity)
}