package com.example.studentchat.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.studentchat.authentication.domain.LogInWithEmailPasswordUseCase
import com.example.studentchat.authentication.domain.SignUpUseCase
import com.example.studentchat.user.data.UserApiModel
import com.google.firebase.auth.FirebaseAuth
import org.koin.java.KoinJavaComponent.inject

class AuthenticationViewModel: ViewModel() {
    private val _logged = MutableLiveData<Boolean>()
    val logged : LiveData<Boolean> = _logged
    private val logInWithEmailPasswordUseCase = LogInWithEmailPasswordUseCase()
    private val signUp: SignUpUseCase by inject(SignUpUseCase::class.java)
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

    suspend fun signUpWithEmailPassword(userApiModel: UserApiModel) {
        signUp(userApiModel)
    }
    override fun onCleared() {
        super.onCleared()
        mAuth.removeAuthStateListener(authStateListener)
    }

}
