package com.example.data.remote.model

import com.example.data.model.Message
import com.example.data.model.User

internal data class ConversationGroupRemote(
    var id: String = "",
    val listInterlocutors: MutableList<User>,
    var messages: List<Message>? = null,
    var title: String = "",
)
