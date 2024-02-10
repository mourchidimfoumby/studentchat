package com.example.studenchat.ui.elements.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studenchat.data.repositories.firebase.FriendsRepository
import com.example.studenchat.databinding.ActivityAddFriendsBinding
import com.example.studenchat.domain.friends.AddFriendsUseCase
import com.example.studenchat.ui.stateholders.adapter.AddFriendsAdapter
import com.example.studenchat.ui.stateholders.viewmodel.FriendsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AddFriendsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddFriendsBinding
    private lateinit var rvAddFriends: RecyclerView
    private lateinit var addFriendsAdapter: AddFriendsAdapter
    private val friendsViewModel : FriendsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddFriendsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvAddFriends = binding.rvAddFriends
        val addFriendsUseCase = AddFriendsUseCase(FriendsRepository())
        addFriendsAdapter = AddFriendsAdapter(emptyList()){user ->
            CoroutineScope(Dispatchers.IO).launch {
                addFriendsUseCase(user)
            }
        }
        rvAddFriends.layoutManager = LinearLayoutManager(this)
        rvAddFriends.adapter = addFriendsAdapter
        friendsViewModel.notFriends.observe(this){userList ->
            addFriendsAdapter.updateUserList(userList)
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }
}