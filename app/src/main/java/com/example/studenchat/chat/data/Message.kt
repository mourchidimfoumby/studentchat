package com.example.studenchat.chat.data

import java.io.Serializable

data class Message(
    val author: String = "",
    val text: String = "",
    var dateTime: String = "",
): Serializable