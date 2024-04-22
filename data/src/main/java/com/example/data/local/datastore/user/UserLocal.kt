package com.example.data.local.datastore.user

import com.example.data.R

data class UserLocal(
    val uid: String,
    val firstName: String,
    val lastName: String,
    val mail: String,
    val password: String,
    val birthday: String,
    val picture: Int = R.drawable.ic_avatar,
)