package com.example.studenchat.authentication.domain

import android.util.Log
import com.example.studenchat.data.repositories.firebase.FirebaseParameters
import com.example.studenchat.data.sources.User
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class SignUpUseCase : FirebaseParameters {
    override val tableName = "user"
    suspend operator fun invoke(user: User) = suspendCoroutine { continuation ->
        auth.createUserWithEmailAndPassword(user.mail, user.password)
            .addOnSuccessListener {
                firebaseDatabase.child(tableName).child(auth.uid.toString()).setValue(user)
                    .addOnSuccessListener {
                        Log.i(javaClass.name, "Registration successful !")
                        continuation.resume(Unit)
                    }
                    .addOnFailureListener { e ->
                        Log.e(javaClass.name, e.message.toString())
                        continuation.resumeWithException(e)
                    }
            }
            .addOnFailureListener { e ->
                Log.e(javaClass.name, e.message.toString())
                continuation.resumeWithException(e)
            }
    }

}