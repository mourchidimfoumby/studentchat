package com.example.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.dao.ConversationDao
import com.example.data.local.dao.FriendsDao
import com.example.data.local.dao.MessageDao
import com.example.data.local.entity.ConversationEntity
import com.example.data.local.entity.FriendsEntity
import com.example.data.local.entity.MessageEntity

@Database(
    entities =
    [ConversationEntity::class,
        FriendsEntity::class,
        MessageEntity::class
    ],
    version = 1
)
internal abstract class StudentChatDatabase : RoomDatabase() {
    abstract fun conversationDao(): ConversationDao
    abstract fun friendsDao(): FriendsDao
    abstract fun messageDao(): MessageDao
}