package com.example.studenchat.data.repository

import com.example.studenchat.data.source.Conversation
import com.example.studenchat.data.source.ConversationGroup
import com.example.studenchat.data.source.User

interface UserConversationGroupRepository: UserConversationRepository, Repository {
    suspend fun modifyTitle(title: String, conversation: Conversation)
    suspend fun addUser(user: User, conversationGroup: ConversationGroup)
    suspend fun removeUser(user: User, conversationGroup: ConversationGroup)
}