package com.example.studenchat.ui.elements.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.studenchat.data.sources.Message
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studenchat.R
import com.example.studenchat.ui.stateholders.adapter.ChatAdapter
import com.example.studenchat.databinding.ActivityChatBinding
import com.example.studenchat.data.sources.User
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatBinding
    private lateinit var recyclerViewChat: RecyclerView
    private lateinit var editText: EditText
    private lateinit var btnSendMessage: FloatingActionButton
    private lateinit var interlocutor: User
    private lateinit var currentUser: User
    private val listMessage = mutableListOf<Message>()
    private lateinit var adapter: ChatAdapter
    private val TAG = "ChatActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        interlocutor = intent.getSerializableExtra("interlocutor") as User
        val topBarView = layoutInflater.inflate(R.layout.toolbar_chat, null)
        val iconBar = topBarView.findViewById<ImageView>(R.id.img_view_avatar_chat)
        val titleBar = topBarView.findViewById<TextView>(R.id.txt_view_fullname_chat)
        iconBar.setImageResource(interlocutor.picture)
        titleBar.text = interlocutor.toString()
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.title = ""
        supportActionBar!!.customView = topBarView
        adapter = ChatAdapter(listMessage){ message ->
            Toast.makeText(this, message.toString(), Toast.LENGTH_SHORT).show()
        }
        recyclerViewChat = binding.rvChat
        editText = binding.editTxtChat
        btnSendMessage = binding.flButtonChat
        recyclerViewChat.adapter = adapter
        recyclerViewChat.layoutManager = LinearLayoutManager(this)

        btnSendMessage.setOnClickListener{
//            if(!editText.text.isNullOrBlank()) sendMessage()
            Toast.makeText(this, "Click", Toast.LENGTH_SHORT).show()
        }
    }
   /* private fun sendMessage(){
        val text = editText.text.toString()
        listMessage.add(Message(currentUser, interlocutor, text, convertDateTimeToString(LocalDateTime.now())))
        adapter.notifyItemInserted(listMessage.size - 1)
        editText.text.clear()
    }*/
}