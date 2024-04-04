package com.example.data.remote.model

import com.google.firebase.database.Exclude

data class ConversationRemote(
    var id: String = "",
    val interlocutors: Map<String, Boolean> = mapOf(),
    var lastMessage: String = ""
) {
    @Exclude
    fun isNotActive(): Boolean = interlocutors.values.all { !it }
}