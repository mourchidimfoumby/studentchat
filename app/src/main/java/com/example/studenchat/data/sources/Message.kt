package com.example.studenchat.data.sources

data class Message(
    val sender: User = User(),
    val text: String = "",
    val dateTime: String = "",
)