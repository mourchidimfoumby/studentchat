package com.example.studenchat.ui.elements.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studenchat.R
import com.example.studenchat.data.sources.Conversation
import com.example.studenchat.ui.stateholders.adapter.FriendsAdapter
import com.example.studenchat.data.sources.User
import com.example.studenchat.databinding.ActivityAddConversationBinding
import com.example.studenchat.ui.stateholders.viewmodel.ConversationViewModel
import com.example.studenchat.ui.stateholders.viewmodel.FriendsViewModel
import com.example.studenchat.ui.stateholders.viewmodel.MainViewModel
import com.google.android.material.textfield.TextInputLayout

class AddConversationActivity: AppCompatActivity() {
    private lateinit var binding: ActivityAddConversationBinding
    private val TAG = AddConversationActivity::class.java.name
    private lateinit var txtViewSearch: TextInputLayout
    private lateinit var rvFriendsConversation: RecyclerView
    private lateinit var friendsAdapter: FriendsAdapter
    private lateinit var txtViewEmptyFriends: TextView
    private val conversationViewModel : ConversationViewModel by viewModels()
    private val friendsViewModel : FriendsViewModel by viewModels()
    private val mainViewModel : MainViewModel by viewModels()
    private val listClicked = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddConversationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        txtViewSearch = binding.txtInputLayoutSearchAddConversation
        rvFriendsConversation = binding.rvFriendsConversation
        txtViewEmptyFriends = binding.txtViewEmptyFriends
        friendsAdapter = FriendsAdapter(emptyList()) { friend ->
            conversationViewModel.createConversation(Conversation(Pair(mainViewModel.user.value!!, friend)))
        }
        rvFriendsConversation.adapter = friendsAdapter
        rvFriendsConversation.layoutManager = LinearLayoutManager(this)
        friendsViewModel.friends.observe(this) { friendsList ->
            friendsAdapter.updateFriendsList(friendsList)
            if (friendsAdapter.itemCount == 0) {
                txtViewEmptyFriends.isVisible = true
                rvFriendsConversation.isVisible = false
            }
            else{
                txtViewEmptyFriends.isVisible = true
                rvFriendsConversation.isVisible = false
            }
        }
    }
}