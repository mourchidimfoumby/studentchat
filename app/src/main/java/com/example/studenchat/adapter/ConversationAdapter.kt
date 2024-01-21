package com.example.studenchat.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studenchat.R
import com.example.studenchat.model.Conversation

class ConversationAdapter(
    private var conversationList: List<Conversation>,
    private val clickListener: (Conversation) -> Unit
) :
    RecyclerView.Adapter<ConversationAdapter.ConversationViewHolder>() {
    inner class ConversationViewHolder(view: View, clickAtPosition: (Int) -> Unit): RecyclerView.ViewHolder(view){
        init {
            view.setOnClickListener {
                clickAtPosition(bindingAdapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConversationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_conversation, parent, false)
        return ConversationViewHolder(view){
            clickListener(conversationList[it])
        }
    }

    override fun getItemCount() = conversationList.size
    override fun onBindViewHolder(holder: ConversationViewHolder, position: Int) {
        holder.itemView.apply {
            val conversation = conversationList[position]
            val title = findViewById<TextView>(R.id.txt_view_title_conversation)
            val lastMessage = findViewById<TextView>(R.id.txt_view_message_conversation)
            val hourConversation = findViewById<TextView>(R.id.txt_view_hour_conversation)
            val imgAvatar = findViewById<ImageView>(R.id.img_view_avatar_user_conversation)

            lastMessage.text = "Dernier message"
            hourConversation.text = "19:00"

            title.text = conversation.title ?: conversation.goodTitlePicture().first

            conversation.picture?.let {
                imgAvatar.setImageResource(it)
            }?: imgAvatar.setImageResource(conversation.goodTitlePicture().second)

        }

    }

}