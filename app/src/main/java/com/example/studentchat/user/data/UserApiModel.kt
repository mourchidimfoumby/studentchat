package com.example.studentchat.user.data

data class UserApiModel(
    var uid: String = "",
    val name: String = "",
    val firstname: String = "",
    val mail: String = "",
    val password: String = "",
    val birthday: String = "",
)