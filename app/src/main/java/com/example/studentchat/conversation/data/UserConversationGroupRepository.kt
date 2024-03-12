package com.example.studentchat.conversation.data

import com.example.studentchat.Repository
import com.example.studentchat.user.data.User

interface UserConversationGroupRepository: UserConversationRepository, Repository {
    suspend fun modifyTitle(title: String, conversation: Conversation)
    suspend fun addUser(user: User, conversationGroup: ConversationGroup)
    suspend fun removeUser(user: User, conversationGroup: ConversationGroup)
}