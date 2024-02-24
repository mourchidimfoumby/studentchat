package com.example.studenchat.data.source

data class Message(
<<<<<<< Updated upstream
    var id: String? = null,
    val sender: User = User(),
    val text: String = "",
    val dateTime: String = "",
)
=======
    val author: String = "",
    val text: String = "",
    val dateTime: Long = 0,
)
>>>>>>> Stashed changes
