package com.example.studenchat.main.conversation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studenchat.R
import com.example.studenchat.data.Conversation

class ConversationAdapter(private var conversationList: List<Conversation>) :
    RecyclerView.Adapter<ConversationAdapter.ConversationViewHolder>() {
    inner class ConversationViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConversationViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_conversation, parent, false)
        return ConversationViewHolder(view)
    }

    override fun getItemCount() = conversationList.size
    override fun onBindViewHolder(holder: ConversationViewHolder, position: Int) {
        holder.itemView.apply {

            val conversation = conversationList[position]
            val username = findViewById<TextView>(R.id.txt_view_user_name_conversation)
            val lastMessage = findViewById<TextView>(R.id.txt_view_message)
            val hourMessage = findViewById<TextView>(R.id.txt_view_hour_message)
            val imgAvatar = findViewById<ImageView>(R.id.img_view_avatar_user_conversation)

            username.text = String.format("%s %s", conversation.user.firstname, conversation.user.name)
            lastMessage.text = conversation.lastMessage
            hourMessage.text = conversation.hourMessage
            imgAvatar.setImageResource(conversation.user.picture)

        }

    }
}