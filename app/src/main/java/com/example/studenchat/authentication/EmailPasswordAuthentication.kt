package com.example.studenchat.authentication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.studenchat.firebase.FirebaseAuthenticationHelper
import com.example.studenchat.firebase.FirebaseAuthenticationHelper.signInWithEmailAndPassword
import com.example.studenchat.main.MainActivity
import com.example.studenchat.R
import com.example.studenchat.data.UserEmailPassword
import com.example.studenchat.databinding.ActivityAuthenticationBinding
import com.example.studenchat.inputIsNotEmpty
import com.google.android.material.textfield.TextInputEditText

class EmailPasswordAuthentication : AppCompatActivity() {
    private lateinit var binding: ActivityAuthenticationBinding
    private lateinit var btnConnection: Button
    private lateinit var btnRegistration: Button
    private lateinit var txtViewConnectError: TextView
    private lateinit var inputMail: TextInputEditText
    private lateinit var inputPassword: TextInputEditText
    private lateinit var user: UserEmailPassword
    private val TAG = EmailPasswordAuthentication::class.java.name
    private val context: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)

            btnConnection = binding.buttonLogin
            btnRegistration = binding.buttonRegistration
            inputMail = binding.inputTextMailLogin
            inputPassword = binding.inputTextPasswordLogin
            txtViewConnectError = binding.txtViewConnectError

            btnConnection.setOnClickListener {
                if (inputIsNotEmpty(listOf(inputMail, inputPassword))) {
                    txtViewConnectError.isVisible = false
                    user = UserEmailPassword(
                        inputMail.text.toString(),
                        inputPassword.text.toString()
                    )
                    signIn()
                } else {
                    txtViewConnectError.text = getString(R.string.error_input_not_empty)
                    txtViewConnectError.isVisible = true
                }
            }

            btnRegistration.setOnClickListener {
                Intent(context, RegistrationActivity::class.java).also {
                    startActivity(it)
                }
            }
        }

    override fun onStart() {
        super.onStart()
        if (FirebaseAuthenticationHelper.userIsConnected()) {
            Intent(context, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }


    private fun signIn(){
        try{
            signInWithEmailAndPassword(user)
            Intent(context, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
        catch (e: Exception){
            txtViewConnectError.text = getString(R.string.error_connection)
            txtViewConnectError.isVisible = true
        }
    }
}