package com.example.studenchat.model

data class Message(
    val sender: User = User(),
    val text: String = "",
    val dateTime: String = "",
)