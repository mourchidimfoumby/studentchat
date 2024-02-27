package com.example.studenchat.data.source

data class Message(
    val author: String = "",
    val text: String = "",
    var dateTime: String = "",
)