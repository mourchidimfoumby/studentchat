package com.example.data.model

import com.example.ui.R

data class Friends(
    val uid: String,
    val firstName: String,
    val lastName: String,
    val mail: String,
    val picture: Int = R.drawable.ic_avatar
) {
    override fun toString(): String = String.format("%s %s", firstName, lastName)
}