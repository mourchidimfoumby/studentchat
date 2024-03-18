package com.example.studentchat.conversation.data

data class ConversationDTO(
    val interlocutors: Map<String, Boolean> = mapOf(),
    var id: String = "",
    var lastMessage: String = ""
)