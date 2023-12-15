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
import com.example.studenchat.databinding.ActivityRegistrationBinding
import com.example.studenchat.inputIsNotEmpty
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegistrationBinding
    /*
    FOR LATER
        private lateinit var inputFirstName: TextInputEditText
        private lateinit var inputName: TextInputEditText
        private lateinit var inputBirthday: TextInputEditText
        private lateinit var spinnerStatus: Spinner
        private lateinit var spinnerGender: Spinner

        inputName = binding.inputTextNameRegistration
        inputFirstName = binding.inputTextFirstnameRegistration
        inputBirthday = binding.inputTextBirthdayRegistration
        spinnerGender = binding.spinnerGenderRegistration
        spinnerStatus = binding.spinnerStatusRegistration
    */
    private lateinit var inputMail: TextInputEditText
    private lateinit var inputPassword: TextInputEditText
    private lateinit var txtViewError: TextView
    private lateinit var btnRgistration: Button
    private lateinit var user: User
    private val TAG = RegistrationActivity::class.java.name
    private val auth = Firebase.auth
    private val context: Context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnRgistration = binding.buttonRegistration
        txtViewError = binding.txtViewRegisterError
        inputMail = binding.inputTextMailRegistration
        inputPassword = binding.inputTextPasswordRegistration

        btnRgistration.setOnClickListener {
            txtViewError.isVisible = false
            val inputList = listOf(
                inputMail,
                inputPassword,
            )
            if(inputIsNotEmpty(inputList) && verifPassword()){
                user = User(inputMail.text.toString(), inputPassword.text.toString())
                registerUser(user)
                Intent(context, MainActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
            else{
                txtViewError.isVisible = true
            }
        }
    }
    private fun registerUser(user: User){
        auth.createUserWithEmailAndPassword(user.mail, user.password)
            .addOnSuccessListener {
                Log.i(TAG, "Registration successful")
            }
            .addOnFailureListener { e->
                Log.e(TAG, e.message.toString())
            }
    }

    private fun verifPassword(): Boolean{
        return if(inputPassword.text.toString().length < 6){
            txtViewError.text = getString(R.string.error_short_password)
            false
        } else{
            true
        }
    }
}