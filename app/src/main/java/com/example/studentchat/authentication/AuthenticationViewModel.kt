package com.example.studentchat.authentication

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentchat.AuthenticationState
import com.example.studentchat.authentication.domain.LogInWithEmailPasswordUseCase
import com.example.studentchat.authentication.domain.SignUpWithEmailPasswordUseCase
import com.example.studentchat.user.data.UserApiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class AuthenticationViewModel: ViewModel() {
    private val _authenticationState: MutableStateFlow<AuthenticationState> =
        MutableStateFlow(AuthenticationState.UNAUTHENTICATED)
    val authenticationState: StateFlow<AuthenticationState> = _authenticationState
    private val logInWithEmailPasswordUseCase: LogInWithEmailPasswordUseCase by inject(
        LogInWithEmailPasswordUseCase::class.java
    )
    private val signUpWithEmailPasswordUseCase: SignUpWithEmailPasswordUseCase by inject(
        SignUpWithEmailPasswordUseCase::class.java
    )

    fun logInWithEmailPassword(mail: String, password: String) {
        logInWithEmailPasswordUseCase(mail, password) {
            it.fold(
                onSuccess = { authState ->
                    _authenticationState.value = authState
                },
                onFailure = { error ->
                    _authenticationState.value = AuthenticationState.ERROR_AUTHENTICATION
                    Log.e(javaClass.name, "Error authentication: ${error.message}")
                }
            )
        }
    }

    fun signUpWithEmailPassword(userApiModel: UserApiModel) {
        viewModelScope.launch {
            signUpWithEmailPasswordUseCase(userApiModel).collectLatest {
                it.fold(
                    onSuccess = { authState ->
                        _authenticationState.value = authState
                    },
                    onFailure = { error ->
                        _authenticationState.value = AuthenticationState.ERROR_AUTHENTICATION
                        Log.e(javaClass.name, "Error authentication: ${error.message}")
                    }
                )
            }
        }
    }
}
