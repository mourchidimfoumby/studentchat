package com.example.studenchat.data.source

data class Message(
    var id: String? = null,
    val sender: User = User(),
    val text: String = "",
    val dateTime: String = "",
)