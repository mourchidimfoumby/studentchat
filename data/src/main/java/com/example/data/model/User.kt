package com.example.data.model

import com.example.ui.R
import com.google.firebase.database.Exclude
import java.io.Serializable

data class User(
    val uid: String,
    val firstName: String,
    val lastName: String,
    val mail: String,
    val picture: Int = R.drawable.ic_avatar,
): Serializable {
    override fun toString(): String =
        String.format("%s %s", firstName, lastName)
}
