package com.example.studentchat.user.data

import com.example.studentchat.R
import com.google.firebase.database.Exclude
import java.io.Serializable

data class User(
    val uid: String = "",
    val name: String = "",
    val firstname: String = "",
    val mail: String = "",
    @get:Exclude
    val picture: Int = R.drawable.ic_avatar,
): Serializable {
    @Exclude
    override fun toString(): String {
        return String.format("%s %s", firstname, name)
    }
}
