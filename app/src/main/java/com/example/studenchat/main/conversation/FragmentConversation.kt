package com.example.studenchat.main.conversation

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studenchat.R
import com.example.studenchat.data.Conversation
import com.example.studenchat.data.Gender
import com.example.studenchat.data.Status
import com.example.studenchat.data.User
import com.example.studenchat.databinding.FragmentConversationBinding
import com.example.studenchat.showPopup
import com.google.android.material.floatingactionbutton.FloatingActionButton

const val TAG = "FragmentConversation"
class FragmentConversation(val parentContext: Context): Fragment(R.layout.fragment_conversation){
    private lateinit var binding : FragmentConversationBinding
    private lateinit var recyclerViewConversation: RecyclerView
    private lateinit var txtViewEmptyConversation: TextView
    private lateinit var floatingButtonConversation: FloatingActionButton
    companion object{
        val users = mutableListOf(
            User(
                "Durant","Sebastien",
                "durantseb@gmail.com","1234567",
                "12/03/2000",Gender.HOMME, Status.ETUDIANT),
            User(
                "Mbappe","Killian",
                "kmbappe@gmail.com","1234567",
                "12/03/2000",Gender.HOMME, Status.ETUDIANT),
            User(
                "Evra","Patrice",
                "evra@gmail.com","1234567",
                "12/03/2000",Gender.HOMME, Status.ENSEIGNANT),
            User(
                "Aluz","Felicia",
                "durantseb@gmail.com","1234567",
                "12/03/2000",Gender.FEMME, Status.ETUDIANT),
            User(
                "Bertide","Louise",
                "durantseb@gmail.com","1234567",
                "12/03/2000",Gender.FEMME, Status.ENSEIGNANT),
            User(
                "Pokemon","Sacha",
                "sacha@gmail.com","1234567",
                "12/03/2002",Gender.HOMME, Status.ETUDIANT),
            User(
                "Dupont","Louise",
                "durantseb@gmail.com","1234567",
                "12/03/1980",Gender.FEMME, Status.ENSEIGNANT)
        )
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentConversationBinding.bind(view)
        recyclerViewConversation = binding.recyclerViewConversation
        txtViewEmptyConversation = binding.txtViewEmptyConversation
        floatingButtonConversation = binding.floatingButtonConversation

        val conversations = mutableListOf<Conversation>()
        for(u in users){
            conversations.add(Conversation(u,"Salut","19:04"))
        }

        val adapter = ConversationAdapter(conversations)
        recyclerViewConversation.adapter = adapter
        recyclerViewConversation.layoutManager = LinearLayoutManager(context)
        if(adapter.itemCount == 0){
            txtViewEmptyConversation.isVisible = true
            recyclerViewConversation.isVisible = false
        }

        floatingButtonConversation.setOnClickListener {
            showPopup(parentContext,view,R.layout.activity_add_conversation)

        }
    }
}