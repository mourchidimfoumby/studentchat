package com.example.studentchat.conversation.data

data class ConversationDTO(
    val interlocutors: List<Map<String, Boolean>>? = null,
    var id: String = "",
    var lastMessage: String? = null
)