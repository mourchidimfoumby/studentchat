package com.example.studenchat.main.conversation

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.studenchat.databinding.ActivityAddConversationBinding
import com.google.android.material.textfield.TextInputLayout

class AddConversationActivity: AppCompatActivity() {
    private lateinit var binding: ActivityAddConversationBinding
    private lateinit var txtViewSearch: TextInputLayout
    private lateinit var rvFriends: RecyclerView
    private lateinit var btnAddConversation: Button
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        binding = ActivityAddConversationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        txtViewSearch = binding.txtInputLayoutSearch
        rvFriends = binding.rvFriendsConversation
        btnAddConversation = binding.btnAddConversation


    }
}