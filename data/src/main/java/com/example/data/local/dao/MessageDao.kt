package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.TABLE_MESSAGE
import com.example.data.local.entity.MessageEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface MessageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(messageEntity: MessageEntity)

    @Update
    suspend fun updateMessage(messageEntity: MessageEntity)

    @Query("SELECT * FROM $TABLE_MESSAGE WHERE conversationId = :conversationId")
    fun getAllMessage(conversationId: String): Flow<List<MessageEntity>>
    @Query(
        """
            SELECT * FROM $TABLE_MESSAGE
            WHERE conversationId = :conversationId
            ORDER BY timestamp DESC
            LIMIT 1
            """
    )
    fun getLastMessage(conversationId: String): Flow<MessageEntity>

    @Query("SELECT * FROM $TABLE_MESSAGE WHERE timestamp = :timestamp AND conversationId = :conversationId")
    suspend fun getMessage(conversationId: String, timestamp: Long): MessageEntity?

    @Delete
    suspend fun deleteMessage(messageEntity: MessageEntity)
}