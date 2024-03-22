package com.example.studentchat.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentchat.authentication.domain.LogInWithEmailPasswordUseCase
import com.example.studentchat.authentication.domain.SignUpWithEmailPasswordUseCase
import com.example.studentchat.user.data.UserApiModel
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class AuthenticationViewModel: ViewModel() {
    private val logInWithEmailPasswordUseCase: LogInWithEmailPasswordUseCase by inject(
        LogInWithEmailPasswordUseCase::class.java
    )
    private val signUpWithEmailPasswordUseCase: SignUpWithEmailPasswordUseCase by inject(
        SignUpWithEmailPasswordUseCase::class.java
    )

    fun logInWithEmailPassword(mail: String, password: String) {
        logInWithEmailPasswordUseCase(mail, password)
    }

    fun signUpWithEmailPassword(userApiModel: UserApiModel) {
        viewModelScope.launch {
            signUpWithEmailPasswordUseCase(userApiModel)
        }
    }
}
