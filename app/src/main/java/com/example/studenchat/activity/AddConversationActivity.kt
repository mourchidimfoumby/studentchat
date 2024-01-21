package com.example.studenchat.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.studenchat.adapter.FriendsAdapter
import com.example.studenchat.databinding.AddConversationLayoutBinding
import com.google.android.material.textfield.TextInputLayout

class AddConversationActivity: AppCompatActivity() {
    private lateinit var binding: AddConversationLayoutBinding
    private lateinit var txtViewSearch: TextInputLayout
    private lateinit var rvFriends: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        binding = AddConversationLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        txtViewSearch = binding.txtInputLayoutSearch
        rvFriends = binding.rvFriendsConversation
        val friendAdapter = FriendsAdapter(mutableListOf()){
            Toast.makeText( this,"Click sur ${it.firstname}", Toast.LENGTH_SHORT).show()
        }
        rvFriends.adapter = friendAdapter
    }
}