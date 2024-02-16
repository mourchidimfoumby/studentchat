package com.example.studenchat.data.interfaces

import com.example.studenchat.data.source.Conversation
import com.example.studenchat.data.source.ConversationGroup
import com.example.studenchat.data.source.User

interface ConversationRepository {
    fun getAllConversations(onDataChangeCallback:(List<Conversation>) -> Unit)
    fun createConversation(conversation: Conversation)
    fun deleteConversation(conversation: Conversation)
    fun modifyTitle(title: String, conversation: Conversation)
    fun addUser(user: User, conversationGroup: ConversationGroup)
    fun removeUser(user: User, conversationGroup: ConversationGroup)

}