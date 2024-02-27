package com.example.studenchat.data.source

import com.example.studenchat.R
import com.google.firebase.database.Exclude
import java.io.Serializable

data class User(
    var uid: String = "",
    val name: String = "",
    val firstname: String = "",
    val mail: String = "",
    val password: String = "",
    val birthday: String = "",
    @get:Exclude
    val picture: Int = R.drawable.ic_avatar,
): Serializable {
    @Exclude
    override fun toString(): String {
        return String.format("%s %s", firstname, name)
    }
}
