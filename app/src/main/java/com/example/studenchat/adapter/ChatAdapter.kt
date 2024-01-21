package com.example.studenchat.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studenchat.R
import com.example.studenchat.getHoursFromStringDateTime
import com.example.studenchat.model.Message
import com.example.studenchat.repository.firebase.FirebaseAuthenticationHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ChatAdapter(
    private val messageList: MutableList<Message>,
    private val clickListener: (Message) -> Unit
):
    RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {
    inner class ChatViewHolder(itemView: View, clickAtPosition: (Int) -> Unit): RecyclerView.ViewHolder(itemView){
        init {
            itemView.setOnClickListener{
                clickAtPosition(bindingAdapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_message_right, parent,false)
        return ChatViewHolder(view){
            clickListener(messageList[it])
        }
    }

    override fun getItemCount() = messageList.size

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.itemView.apply {
            val message = messageList[position]
            val author = findViewById<TextView>(R.id.txt_view_author)
            val text = findViewById<TextView>(R.id.txt_view_message)
            val hour = findViewById<TextView>(R.id.txt_view_hour_message)
            CoroutineScope(Dispatchers.IO).launch {
                val currentUser = async{ FirebaseAuthenticationHelper.getUser()}
                if(message.sender == currentUser.await())
                    author.text = context.getString(R.string.string_self_author)
                else author.text = message.sender.toString()
            }

            text.text = message.text
            hour.text = getHoursFromStringDateTime(message.dateTime)
        }
    }
}