package com.example.data.remote.model

internal data class UserRemote(
    var uid: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val mail: String = "",
    val password: String = "",
    val birthday: String = "",
)