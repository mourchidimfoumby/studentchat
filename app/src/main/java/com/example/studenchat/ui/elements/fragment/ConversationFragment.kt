package com.example.studenchat.ui.elements.activity.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studenchat.R
import com.example.studenchat.ui.elements.activity.AddConversationActivity
import com.example.studenchat.ui.stateholders.adapter.ConversationAdapter
import com.example.studenchat.databinding.FragmentConversationsBinding
import com.example.studenchat.ui.stateholders.viewmodel.ConversationViewModel
import com.example.studenchat.utils.ToastUtils.Companion.clickToast
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ConversationFragment: Fragment(R.layout.fragment_conversations){
    private lateinit var binding : FragmentConversationsBinding
    private lateinit var recyclerViewConversation: RecyclerView
    private lateinit var txtViewEmptyConversation: TextView
    private lateinit var floatingButtonConversation: FloatingActionButton
    private lateinit var conversationAdapter: ConversationAdapter
    private val viewModel: ConversationViewModel by viewModels()

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentConversationsBinding.bind(view)
        recyclerViewConversation = binding.rvConversation
        txtViewEmptyConversation = binding.txtViewEmptyConversation
        floatingButtonConversation = binding.floatingButtonConversation
        conversationAdapter = ConversationAdapter(emptyList()) { conversation ->
            requireContext().clickToast("Click sur ${conversation.title}")
        }
        recyclerViewConversation.adapter = conversationAdapter
        recyclerViewConversation.layoutManager = LinearLayoutManager(context)
        viewModel.conversation.observe(viewLifecycleOwner) { conversationsList ->
                conversationAdapter.updateConversationList(conversationsList)
                if (conversationAdapter.itemCount == 0) {
                    txtViewEmptyConversation.isVisible = true
                    recyclerViewConversation.isVisible = false
                }
                else{
                    txtViewEmptyConversation.isVisible = true
                    recyclerViewConversation.isVisible = false
                }
            }

        floatingButtonConversation.setOnClickListener {
            Intent(requireContext(), AddConversationActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}
