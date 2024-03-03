package com.example.studenchat.conversation.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studenchat.R
import com.example.studenchat.chat.ui.ChatActivity
import com.example.studenchat.chat.ui.ERROR_OPEN_CHAT
import com.example.studenchat.conversation.data.Conversation
import com.example.studenchat.conversation.ui.stateholder.ConversationAdapter
import com.example.studenchat.conversation.ui.stateholder.ConversationViewModel
import com.example.studenchat.databinding.FragmentConversationBinding
import com.example.studenchat.utils.messageToast
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ConversationFragment: Fragment(R.layout.fragment_conversation){
    private lateinit var binding : FragmentConversationBinding
    private lateinit var recyclerViewConversation: RecyclerView
    private lateinit var txtViewEmptyConversation: TextView
    private lateinit var floatingButtonConversation: FloatingActionButton
    private lateinit var progressBar: ProgressBar
    private lateinit var conversationAdapter: ConversationAdapter
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private val viewModel: ConversationViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentConversationBinding.bind(view)
        resultLauncher = registerForActivityResult(StartActivityForResult()) { result ->
            if (result.resultCode == ERROR_OPEN_CHAT) {
                requireContext().messageToast("Erreur: Impossible d'ouvrir le chat")
            }
        }
        recyclerViewConversation = binding.rvConversation
        txtViewEmptyConversation = binding.txtViewEmptyConversation
        floatingButtonConversation = binding.floatingButtonConversation
        progressBar = binding.progressBarConversation
        progressBar.isVisible = true
        conversationAdapter = ConversationAdapter(emptyList()) { conversation ->
            openChat(conversation)
        }
        recyclerViewConversation.adapter = conversationAdapter
        recyclerViewConversation.layoutManager = LinearLayoutManager(context)

        viewModel.conversations.observe(viewLifecycleOwner) { conversationsList ->
            conversationAdapter.updateConversationList(conversationsList)
            progressBar.isVisible = false
            if (conversationAdapter.itemCount == 0) {
                txtViewEmptyConversation.isVisible = true
                recyclerViewConversation.isVisible = false
            }
            else{
                txtViewEmptyConversation.isVisible = false
                recyclerViewConversation.isVisible = true
            }
        }

//        floatingButtonConversation.setOnClickListener {
//            Intent(requireContext(), AddConversationActivity::class.java).also {
//                startActivity(it)
//            }
//        }
    }

    private fun openChat(conversation: Conversation) {
        Intent(requireContext(), ChatActivity::class.java).also {
            it.putExtra("conversation", conversation)
            resultLauncher.launch(it)
        }
    }
}