package com.example.studenchat.authentication.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.studenchat.R
import com.example.studenchat.authentication.domain.LogInUseCase
import com.example.studenchat.databinding.ActivityAuthenticationBinding
import com.example.studenchat.ui.elements.activity.MainActivity
import com.example.studenchat.utils.InputUtils.Companion.inputIsNotEmpty
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class AuthenticationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthenticationBinding
    private lateinit var btnConnection: Button
    private lateinit var btnRegistration: Button
    private lateinit var txtViewConnectError: TextView
    private lateinit var inputMail: TextInputEditText
    private lateinit var inputPassword: TextInputEditText
    private lateinit var progressBar: ProgressBar
    private val TAG = AuthenticationActivity::class.java.name
    private val context: Context = this
    private val logInUseCase = LogInUseCase()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        btnConnection = binding.buttonLogin
        btnRegistration = binding.buttonRegistration
        inputMail = binding.inputTextMailLogin
        inputPassword = binding.inputTextPasswordLogin
        txtViewConnectError = binding.txtViewConnectError
        progressBar = binding.progressBarAuthentication

        btnConnection.setOnClickListener {
            progressBar.isVisible = true
            if (!inputIsNotEmpty(listOf(inputMail, inputPassword))) {
                progressBar.isVisible = false
                txtViewConnectError.text = getString(R.string.error_input_not_empty)
                txtViewConnectError.isVisible = true
            } else {
                txtViewConnectError.isVisible = false
                signIn()
            }
        }

        btnRegistration.setOnClickListener {
            Intent(context, RegistrationActivity::class.java).also {
                startActivity(it)
            }
        }
    }
    private fun signIn(){
        lifecycleScope.launch {
            try{
                logInUseCase(inputMail.text.toString(), inputPassword.text.toString())
                    progressBar.isVisible = false
                    Intent(this@AuthenticationActivity, MainActivity::class.java).also {
                        startActivity(it)
                        finish()
                    }

            }
            catch (e: Exception){
                progressBar.isVisible = false
                txtViewConnectError.text = getString(R.string.error_connection)
                txtViewConnectError.isVisible = true
            }
        }
    }
}