package com.example.studenchat.data

import com.google.android.material.imageview.ShapeableImageView

data class Conversation(
    val user: User,
    val lastMessage: String,
    val hourMessage: String
)
