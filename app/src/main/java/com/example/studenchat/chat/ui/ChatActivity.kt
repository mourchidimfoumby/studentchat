package com.example.studenchat.chat.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studenchat.MainActivity
import com.example.studenchat.R
import com.example.studenchat.chat.data.Message
import com.example.studenchat.chat.ui.stateholder.ChatAdapter
import com.example.studenchat.chat.ui.stateholder.ChatViewModel
import com.example.studenchat.conversation.data.Conversation
import com.example.studenchat.databinding.ActivityChatBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatBinding
    private lateinit var editTxtChat: EditText
    private lateinit var floatingButtonChat: FloatingActionButton
    private lateinit var recyclerViewChat: RecyclerView
    private lateinit var toolbarChat: Toolbar
    private lateinit var txtViewUserName: TextView
    private lateinit var imgViewUserAvatar: ImageView
    private var allMessage = listOf<Message>()
    private val chatAdapter = ChatAdapter(allMessage)
    private val chatViewModel: ChatViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        val conversation = intent.getSerializableExtra("conversation") as Conversation?
        if (conversation == null) {
            Intent(this, MainActivity::class.java).also {
                setResult(Activity.RESULT_CANCELED, it)
                finish()
            }
        }
        setContentView(binding.root)
        toolbarChat = binding.layoutToolbarChat.findViewById(R.id.toolbar_chat)
        setSupportActionBar(toolbarChat)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        chatViewModel(conversation!!)

        editTxtChat = binding.editTxtChat
        floatingButtonChat = binding.flButtonChat
        recyclerViewChat = binding.rvChat
        txtViewUserName = toolbarChat.findViewById(R.id.txt_view_user_name_chat)
        imgViewUserAvatar = toolbarChat.findViewById(R.id.img_view_avatar_user_chat)
        val interlocutor = conversation.otherUser()

        txtViewUserName.text = interlocutor.toString()
        imgViewUserAvatar.setImageResource(interlocutor.picture)
        recyclerViewChat.layoutManager = LinearLayoutManager(this)
        recyclerViewChat.adapter = chatAdapter

        chatViewModel.allMessages.observe(this) { messageList ->
            allMessage = messageList
            chatAdapter.updateList(allMessage)
        }

        floatingButtonChat.setOnClickListener {
            if (editTxtChat.text.isNotBlank()) {
                chatViewModel.sendMessage(conversation, editTxtChat.text.toString())
                editTxtChat.text.clear()
            }
        }
    }
}