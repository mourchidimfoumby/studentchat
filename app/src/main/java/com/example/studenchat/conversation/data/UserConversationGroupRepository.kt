package com.example.studenchat.conversation.data

import com.example.studenchat.Repository
import com.example.studenchat.user.data.User

interface UserConversationGroupRepository: UserConversationRepository, Repository {
    suspend fun modifyTitle(title: String, conversation: Conversation)
    suspend fun addUser(user: User, conversationGroup: ConversationGroup)
    suspend fun removeUser(user: User, conversationGroup: ConversationGroup)
}