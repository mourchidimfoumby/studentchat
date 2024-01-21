package com.example.studenchat.repository.firebase

import android.util.Log
import com.example.studenchat.model.User
import com.example.studenchat.repository.firebase.FirebaseDatabaseHelper.firebaseDatabase
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


object FirebaseAuthenticationHelper {
    private val auth = Firebase.auth
    private val TAG = FirebaseAuthenticationHelper::class.java.name

    fun signInWithEmailAndPassword(mail: String, password: String){
        auth.signInWithEmailAndPassword(mail, password)
            .addOnSuccessListener {
                Log.i(TAG, "Connection successful")
                return@addOnSuccessListener
            }
            .addOnFailureListener { e->
                Log.e(TAG, e.message.toString())
            }
    }

    fun userIsConnected(): Boolean{
        return auth.currentUser != null
    }

    fun signOut(){
        auth.signOut()
    }

    fun registration(user: User){
        auth.createUserWithEmailAndPassword(user.mail, user.password)
            .addOnSuccessListener {
                firebaseDatabase.child("user").child(auth.uid.toString()).setValue(user)
                    .addOnSuccessListener {
                        Log.i(TAG, "Registration successful")
                    }
                    .addOnFailureListener { e ->
                        Log.e(TAG, e.message.toString())
                    }
                return@addOnSuccessListener
            }
            .addOnFailureListener { e ->
                Log.e(TAG, e.message.toString())
            }
    }

    suspend fun getUser(): User {
        return suspendCoroutine { continuation ->
            firebaseDatabase.child("user").child(auth.uid.toString())
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val user = snapshot.getValue(User::class.java)
                        if(user != null) continuation.resume(user)
                        else continuation.resumeWithException(Exception("Utilisateur non trouvé"))
                    }
                    override fun onCancelled(error: DatabaseError) {
                        continuation.resumeWithException(Exception(error.message))
                    }
                })
        }
    }
}