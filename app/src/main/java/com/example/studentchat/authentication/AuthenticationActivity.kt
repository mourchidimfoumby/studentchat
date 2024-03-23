package com.example.studentchat.authentication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.studentchat.AuthenticationState
import com.example.studentchat.MainActivity
import com.example.studentchat.R
import com.example.studentchat.authentication.domain.IsLoggedInUseCase
import com.example.studentchat.databinding.ActivityAuthenticationBinding
import com.example.studentchat.utils.inputIsEmpty
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class AuthenticationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthenticationBinding
    private lateinit var btnConnection: Button
    private lateinit var btnRegistration: Button
    private lateinit var txtViewConnectError: TextView
    private lateinit var inputMail: TextInputEditText
    private lateinit var inputPassword: TextInputEditText
    private lateinit var progressBar: ProgressBar
    private val isLoggedInUseCase: IsLoggedInUseCase by inject(
        IsLoggedInUseCase::class.java
    )
    private val authenticationViewModel : AuthenticationViewModel by viewModels()
    private val TAG = AuthenticationActivity::class.java.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        if (isLoggedInUseCase()) {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        lifecycleScope.launch {
            authenticationViewModel.authenticationState.collect { state ->
                if (state == AuthenticationState.AUTHENTICATED) {
                    progressBar.isVisible = false
                    Intent(this@AuthenticationActivity, MainActivity::class.java).also {
                        startActivity(it)
                        finish()
                    }
                } else if (state == AuthenticationState.ERROR_AUTHENTICATION) {
                    progressBar.isVisible = false
                    txtViewConnectError.text = getString(R.string.error_connection)
                    txtViewConnectError.isVisible = true
                }
            }
        }

        btnConnection = binding.buttonLogin
        btnRegistration = binding.buttonRegistration
        inputMail = binding.inputTextMailLogin
        inputPassword = binding.inputTextPasswordLogin
        txtViewConnectError = binding.txtViewConnectError
        progressBar = binding.progressBarAuthentication

        btnConnection.setOnClickListener {
            if (inputIsEmpty(listOf(inputMail, inputPassword))){
                progressBar.isVisible = false
                txtViewConnectError.text = getString(R.string.error_input_not_empty)
                txtViewConnectError.isVisible = true
            }
            else signIn(inputMail.text.toString(), inputPassword.text.toString())
        }

        btnRegistration.setOnClickListener {
            Intent(this, RegistrationActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    private fun signIn(mail: String, password: String){
        progressBar.isVisible = true
        txtViewConnectError.isVisible = false
        authenticationViewModel.logInWithEmailPassword(mail, password)
    }
}
