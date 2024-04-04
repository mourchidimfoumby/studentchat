package com.example.data.model

data class Friends(
    val uid: String,
    val firstName: String,
    val lastName: String,
    val mail: String,
) {
    override fun toString(): String = String.format("%s %s", firstName, lastName)
}