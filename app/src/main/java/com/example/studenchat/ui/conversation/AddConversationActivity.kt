package com.example.studenchat.ui.conversation

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studenchat.data.source.Conversation
import com.example.studenchat.data.repository.UserRepository
import com.example.studenchat.databinding.ActivityAddConversationBinding
import com.example.studenchat.domain.usecase.GetCurrentUserUseCase
import com.example.studenchat.ui.friends.FriendsAdapter
import com.example.studenchat.ui.friends.FriendsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent.inject

class AddConversationActivity: AppCompatActivity() {
    private lateinit var binding: ActivityAddConversationBinding
    private val TAG = AddConversationActivity::class.java.name
    private lateinit var rvFriendsConversation: RecyclerView
    private lateinit var friendsAdapter: FriendsAdapter
    private lateinit var txtViewEmptyFriends: TextView
    private val conversationViewModel : ConversationViewModel by viewModels()
    private val friendsViewModel : FriendsViewModel by viewModels()
    private val getCurrentUserUseCase: GetCurrentUserUseCase by inject(GetCurrentUserUseCase::class.java)
    private val userRepository: UserRepository by inject(UserRepository::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddConversationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        rvFriendsConversation = binding.rvFriendsConversation
        txtViewEmptyFriends = binding.txtViewEmptyFriends
        rvFriendsConversation.adapter = friendsAdapter
        rvFriendsConversation.layoutManager = LinearLayoutManager(this)
        friendsViewModel.friends.observe(this) { friendsList ->
            Log.d("ICI", friendsList.toString())
            if (friendsAdapter.itemCount == 0) {
                txtViewEmptyFriends.isVisible = true
                rvFriendsConversation.isVisible = false
            }
            else{
                txtViewEmptyFriends.isVisible = false
                rvFriendsConversation.isVisible = true
            }
        }
    }
}