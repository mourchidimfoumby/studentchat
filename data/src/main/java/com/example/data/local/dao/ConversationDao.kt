package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.TABLE_CONVERSATION
import com.example.data.local.entity.ConversationEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface ConversationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConversation(conversationEntity: ConversationEntity)

    @Update
    suspend fun updateConversation(conversationEntity: ConversationEntity)

    @Query("SELECT * FROM $TABLE_CONVERSATION")
    fun getAllConversation(): Flow<List<ConversationEntity>>

    @Query("SELECT * FROM $TABLE_CONVERSATION WHERE id = :conversationId")
    suspend fun getConversation(conversationId: String): ConversationEntity?

    @Delete
    suspend fun deleteConversation(conversationEntity: ConversationEntity)
}