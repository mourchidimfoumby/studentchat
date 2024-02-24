package com.example.studenchat.data.source

import com.example.studenchat.R
<<<<<<< Updated upstream
=======
import com.google.firebase.database.DataSnapshot
>>>>>>> Stashed changes
import com.google.firebase.database.Exclude
import java.io.Serializable

data class User(
<<<<<<< Updated upstream
    val uid: String = "",
=======
    var uid: String = "",
>>>>>>> Stashed changes
    val name: String = "",
    val firstname: String = "",
    val mail: String = "",
    val password: String = "",
    val birthday: String = "",
    @get:Exclude
    val picture: Int = R.drawable.ic_avatar,
): Serializable {
<<<<<<< Updated upstream
=======
    constructor(uid: String, snapshot: DataSnapshot) : this(
        uid = uid,
        name = snapshot.child("name").getValue(String::class.java) ?: "",
        firstname = snapshot.child("firstname").getValue(String::class.java) ?: "",
        mail = snapshot.child("mail").getValue(String::class.java) ?: "",
        password = snapshot.child("password").getValue(String::class.java) ?: "",
        birthday = snapshot.child("birthday").getValue(String::class.java) ?: ""
    )
>>>>>>> Stashed changes
    @Exclude
    override fun toString(): String {
        return String.format("%s %s", firstname, name)
    }
}
