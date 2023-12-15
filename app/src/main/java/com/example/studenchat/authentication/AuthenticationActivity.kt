package com.example.studenchat.authentication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.studenchat.MainActivity
import com.example.studenchat.R
import com.example.studenchat.data.User
import com.example.studenchat.databinding.ActivityAuthenticationBinding
import com.example.studenchat.inputIsNotEmpty
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthenticationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthenticationBinding
    private lateinit var btnConnection: Button
    private lateinit var btnRegistration: Button
    private lateinit var txtViewConnectError: TextView
    private lateinit var inputUsername: TextInputEditText
    private lateinit var inputPassword: TextInputEditText
    private lateinit var user: User
    private val TAG = AuthenticationActivity::class.java.name
    private val context: Context = this
    private val auth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)

            btnConnection = binding.buttonLogin
            btnRegistration = binding.buttonRegistration
            inputUsername = binding.inputTextUsernameLogin
            inputPassword = binding.inputTextPasswordLogin
            txtViewConnectError = binding.txtViewConnectError

            btnConnection.setOnClickListener {
                if (inputIsNotEmpty(listOf(inputUsername, inputPassword))) {
                    txtViewConnectError.isVisible = false
                    user = User(inputUsername.text.toString(), inputPassword.text.toString())
                    signIn(user)
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
        if (userIsConnected()) {
            Intent(context, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }

    private fun userIsConnected(): Boolean{
        return auth.currentUser != null
    }
    private fun signIn(user: User){
        auth.signInWithEmailAndPassword(user.mail, user.password)
            .addOnSuccessListener {
                Log.i(TAG, "Connection successful")
            }
            .addOnFailureListener { e->
                Log.e(TAG, e.message.toString())
                txtViewConnectError.text = getString(R.string.error_connection)
                txtViewConnectError.isVisible = true
            }
    }
}