package com.example.studenchat.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.studenchat.databinding.ActivityAuthenticationBinding
import com.google.android.material.textfield.TextInputEditText

class AuthenticationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthenticationBinding
    private lateinit var btnConnection: Button
    private lateinit var txtViewRegitration: TextView
    private lateinit var txtViewConnectError: TextView
    private lateinit var inputUsername: TextInputEditText
    private lateinit var inputPaswword: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnConnection = binding.buttonLogin
        txtViewRegitration = binding.txtViewRegistration
        inputUsername = binding.inputTextUsernameLogin
        inputPaswword = binding.inputTextMdpLogin
        txtViewConnectError = binding.txtViewConnectError

        btnConnection.setOnClickListener {

        }

    }

    private fun inputIsNotEmpty(): Boolean{
        val passNotEmpty = inputPaswword.text!!.isNotBlank()
        val usernameNotEmpty = inputUsername.text!!.isNotBlank()
        return passNotEmpty && usernameNotEmpty
    }
}