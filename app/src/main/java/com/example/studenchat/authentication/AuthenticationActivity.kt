package com.example.studenchat.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.studenchat.R
import com.example.studenchat.data.User
import com.example.studenchat.databinding.ActivityAuthenticationBinding
import com.google.android.material.textfield.TextInputEditText
import android.widget.ProgressBar
import androidx.activity.viewModels
import com.example.studenchat.utils.InputUtils.Companion.inputIsEmpty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
class AuthenticationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthenticationBinding
    private lateinit var btnConnection: Button
    private lateinit var btnRegistration: Button
    private lateinit var txtViewConnectError: TextView
    private lateinit var inputMail: TextInputEditText
    private lateinit var inputPassword: TextInputEditText
    private lateinit var progressBar: ProgressBar
    private val authenticationViewModel : AuthenticationViewModel by viewModels()
    private val TAG = AuthenticationActivity::class.java.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnConnection = binding.buttonLogin
        btnRegistration = binding.buttonRegistration
        inputMail = binding.inputTextUsernameLogin
        inputPassword = binding.inputTextPasswordLogin
        txtViewConnectError = binding.txtViewConnectError

        btnConnection.setOnClickListener {
            progressBar.isVisible = true
            if (!inputIsEmpty(listOf(inputMail, inputPassword)))
                signIn(inputMail.text.toString(), inputPassword.text.toString())
            else {
                progressBar.isVisible = false
                txtViewConnectError.text = getString(R.string.error_input_not_empty)
                txtViewConnectError.isVisible = true
            }
        }

            btnRegistration.setOnClickListener {
                Intent(this, RegistrationActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
    private fun signIn(mail: String, password: String){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                txtViewConnectError.isVisible = false
                authenticationViewModel.logInWithEmailPassword(mail, password)
            } catch (e: Exception) {
                progressBar.isVisible = false
                txtViewConnectError.text = getString(R.string.error_connection)
                txtViewConnectError.isVisible = true
            }
        }
    }
}
