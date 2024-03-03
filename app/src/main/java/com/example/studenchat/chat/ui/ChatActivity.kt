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
import com.example.studenchat.utils.observeOnce
import com.google.android.material.floatingactionbutton.FloatingActionButton

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
    private var allMessage = listOf<Message>()
    private val chatViewModel: ChatViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        val testConversation = intent.getSerializableExtra("conversation") as Conversation?
        if (testConversation == null) {
            Intent(this, MainActivity::class.java).also {
                setResult(ERROR_OPEN_CHAT, it)
                finish()
            }
        } else conversation = testConversation

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
        chatAdapter = ChatAdapter(allMessage, conversation, recyclerViewChat)
        recyclerViewChat.layoutManager = LinearLayoutManager(this)
        recyclerViewChat.adapter = chatAdapter

        chatViewModel.allMessages.observe(this) { messageList ->
            allMessage = messageList
            chatAdapter.updateList(allMessage)
            recyclerViewChat.post {
                recyclerViewChat.scrollToPosition(chatAdapter.itemCount - 1)
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