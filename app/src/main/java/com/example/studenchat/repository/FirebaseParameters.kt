package com.example.studenchat.repository

import com.example.studenchat.repository.firebase.FirebaseDatabaseHelper.URL_DATABASE
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

interface FirebaseParameters {
    val firebaseDatabase: DatabaseReference
        get() = FirebaseDatabase.getInstance(URL_DATABASE).reference
    val tableName: String
    val auth: FirebaseAuth
        get() = Firebase.auth
}