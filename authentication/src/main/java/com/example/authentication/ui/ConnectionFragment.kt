package com.example.authentication.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.authentication.AuthenticationState
import com.example.authentication.R
import com.example.authentication.databinding.FragmentConnectionBinding
import com.example.authentication.inputIsEmpty
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

class ConnectionFragment : Fragment(R.layout.fragment_connection) {
    private lateinit var binding: FragmentConnectionBinding
    private lateinit var btnConnection: Button
    private lateinit var btnRegistration: Button
    private lateinit var txtViewConnectError: TextView
    private lateinit var inputMail: TextInputEditText
    private lateinit var inputPassword: TextInputEditText
    private lateinit var progressBar: ProgressBar
    private val authenticationViewModel : AuthenticationViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()
        binding = FragmentConnectionBinding.bind(view)
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
            (activity as? AuthenticationActivity)?.addFragment(RegistrationFragment())
        }

        lifecycleScope.launch {
            authenticationViewModel.authenticationState.collect { state ->
               if (state == AuthenticationState.ERROR_AUTHENTICATION) {
                    progressBar.isVisible = false
                    txtViewConnectError.text = getString(R.string.error_connection)
                    txtViewConnectError.isVisible = true
                }
            }
        }
    }

    private fun signIn(mail: String, password: String){
        progressBar.isVisible = true
        txtViewConnectError.isVisible = false
        authenticationViewModel.logInWithEmailPassword(mail, password)
    }
}