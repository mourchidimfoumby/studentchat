package com.example.studenchat.chat.ui.stateholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studenchat.R
import com.example.studenchat.chat.data.Message

class ChatAdapter(private val messageList: List<Message>): RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {
    inner class ChatViewHolder(view: View): RecyclerView.ViewHolder(view)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chat_sender, parent, false)
        return ChatViewHolder(view)
    }

    override fun getItemCount(): Int = messageList.size

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.itemView.apply {
            val message = messageList[position]
            val author = findViewById<TextView>(R.id.txt_view_author_chat)
            val text = findViewById<TextView>(R.id.txt_view_message_chat)
            val hour = findViewById<TextView>(R.id.txt_view_hour_chat)

            author.text = message.author
            text.text = message.text
            hour.text = message.dateTime
        }
    }
}