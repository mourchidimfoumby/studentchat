package com.example.data

import com.example.data.model.User
import com.example.data.remote.model.UserRemote

fun User.toUserRemote() = UserRemote(
    this.uid,
    this.firstName,
    this.lastName,
    this.mail
)

fun UserRemote.toUser() = User(
    this.uid,
    this.firstName,
    this.lastName,
    this.mail
)