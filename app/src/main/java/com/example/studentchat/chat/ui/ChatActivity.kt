package com.example.studentchat.chat.ui

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import chat.ui.stateholder.ChatAdapter
import com.example.data.model.Conversation
import com.example.studentchat.MainActivity
import com.example.studentchat.R
import com.example.studentchat.chat.ui.stateholder.ChatAdapter
import com.example.studentchat.chat.ui.stateholder.ChatViewModel
import com.example.studentchat.databinding.ActivityChatBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

const val ERROR_OPEN_CHAT = 1000;
class ChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatBinding
    private lateinit var editTxtChat: EditText
    private lateinit var floatingButtonChat: FloatingActionButton
    private lateinit var recyclerViewChat: RecyclerView
    private lateinit var toolbarChat: Toolbar
    private lateinit var txtViewUserName: TextView
    private lateinit var imgViewUserAvatar: ImageView
    private lateinit var chatAdapter: ChatAdapter
    private lateinit var conversation: Conversation
    private val chatViewModel: ChatViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        val testConversation = intent.getSerializableExtra("conversation") as Conversation?
        testConversation?.let {
            conversation = it
        } ?: run {
            Intent(this, MainActivity::class.java).also {
                setResult(ERROR_OPEN_CHAT, it)
                finish()
            }
        }

        setContentView(binding.root)
        toolbarChat = binding.layoutToolbarChat.findViewById(R.id.toolbar_chat)
        setSupportActionBar(toolbarChat)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        chatViewModel(conversation)

        editTxtChat = binding.editTxtChat
        floatingButtonChat = binding.flButtonChat
        recyclerViewChat = binding.rvChat
        txtViewUserName = toolbarChat.findViewById(R.id.txt_view_user_name_chat)
        imgViewUserAvatar = toolbarChat.findViewById(R.id.img_view_avatar_user_chat_toolbar)
        val interlocutor = conversation.otherUser()
        txtViewUserName.text = interlocutor.toString()
        imgViewUserAvatar.setImageResource(interlocutor.picture)
        recyclerViewChat.layoutManager = LinearLayoutManager(this@ChatActivity)
        chatAdapter = ChatAdapter(mutableListOf(), conversation, recyclerViewChat)
        recyclerViewChat.adapter = chatAdapter

        lifecycleScope.launch {
            chatViewModel.message.collect { message ->
                message?.let {
                    chatAdapter.addMessage(it)
                    recyclerViewChat.post {
                        recyclerViewChat.scrollToPosition(chatAdapter.itemCount - 1)
                    }
                }
            }
        }

        floatingButtonChat.setOnClickListener {
            if (editTxtChat.text.isNotBlank()) {
                chatViewModel.sendMessage(editTxtChat.text.toString())
                editTxtChat.text.clear()
            }
        }
    }
}