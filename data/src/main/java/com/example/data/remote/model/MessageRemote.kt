package com.example.data.remote.model

internal data class MessageRemote(
    var author: String = "",
    val text: String = "",
    var timestamp: Long = 0,
    val conversationId: String = ""
)
