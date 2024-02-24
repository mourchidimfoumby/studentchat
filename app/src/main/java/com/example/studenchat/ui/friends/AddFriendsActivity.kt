package com.example.studenchat.ui.friends

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studenchat.databinding.ActivityAddFriendsBinding
import com.example.studenchat.domain.usecase.AddFriendsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class AddFriendsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddFriendsBinding
    private lateinit var rvAddFriends: RecyclerView
    private lateinit var addFriendsAdapter: AddFriendsAdapter
    private val friendsViewModel : FriendsViewModel by viewModels()
    private val addFriendsUseCase: AddFriendsUseCase by inject(AddFriendsUseCase::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddFriendsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvAddFriends = binding.rvAddFriends
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