package com.example.studentchat.conversation.data

import com.google.firebase.database.Exclude

data class ConversationDTO(
    var id: String = "",
    val interlocutors: Map<String, Boolean> = mapOf(),
    var lastMessage: String = ""
) {
    @Exclude
    fun isNotActive(): Boolean = interlocutors.values.all { !it }

}