package com.example.data.remote.model

import com.google.firebase.database.Exclude

internal data class ConversationRemote(
    var id: String = "",
    val interlocutors: Map<String, Boolean> = mapOf(),
    var lastMessageId: String = ""
) {
    @Exclude
    fun isNotActive(): Boolean = interlocutors.values.all { !it }
}