package com.example.studenchat.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.studenchat.authentication.domain.LogInWithEmailPasswordUseCase
import com.example.studenchat.authentication.domain.SignUpUseCase
import com.example.studenchat.data.source.User
import com.google.firebase.auth.FirebaseAuth

class AuthenticationViewModel: ViewModel() {
    private val _logged = MutableLiveData<Boolean>()
    val logged : LiveData<Boolean> = _logged
    private val logInWithEmailPasswordUseCase = LogInWithEmailPasswordUseCase()
    private val signUp = SignUpUseCase()
    private val mAuth = FirebaseAuth.getInstance()
    private val authStateListener: FirebaseAuth.AuthStateListener =
        FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            _logged.value = user != null
        }

    init {
        mAuth.addAuthStateListener(authStateListener)
    }
    suspend fun logInWithEmailPassword(mail: String, password: String){
        logInWithEmailPasswordUseCase(mail, password)
    }
    suspend fun signUpWithEmailPassword(user: User){
        signUp(user)
    }
    override fun onCleared() {
        super.onCleared()
        mAuth.removeAuthStateListener(authStateListener)
    }

}
