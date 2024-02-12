package com.example.studenchat.data.repositories.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

interface FirebaseParameters {
    val urlDatabase: String
        get() = "https://studentchat-a99ae-default-rtdb.europe-west1.firebasedatabase.app/"
    val firebaseDatabase: DatabaseReference
        get() = FirebaseDatabase.getInstance(urlDatabase).reference

    val tableName: String
    val auth: FirebaseAuth
        get() = Firebase.auth
    val userId
        get() = Firebase.auth.uid

}