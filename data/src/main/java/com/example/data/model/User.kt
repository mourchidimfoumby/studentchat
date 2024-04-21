package com.example.data.model

import com.example.data.R
import java.io.Serializable

data class User(
    val uid: String,
    val firstName: String,
    val lastName: String,
    val mail: String,
    val birthday: String,
    val password: String,
    val picture: Int = R.drawable.ic_avatar,
) : Serializable {
    override fun toString(): String = String.format("%s %s", firstName, lastName)
}
