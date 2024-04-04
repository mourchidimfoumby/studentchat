package com.example.data.model

data class Friends(
    val uid: String,
    val name: String,
    val firstname: String,
    val mail: String,
) {
    override fun toString(): String = String.format("%s %s", firstname, name)
}