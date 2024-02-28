package com.example.studenchat.friends.ui

import androidx.appcompat.app.AppCompatActivity

class AddFriendsActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityAddFriendsBinding
//    private lateinit var rvAddFriends: RecyclerView
//    private lateinit var addFriendsAdapter: AddFriendsAdapter
//    private val friendsViewModel : FriendsViewModel by viewModels()
//    private val addFriendsUseCase: AddFriendsUseCase by inject(AddFriendsUseCase::class.java)
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityAddFriendsBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        rvAddFriends = binding.rvAddFriends
//        addFriendsAdapter = AddFriendsAdapter(emptyList()){user ->
//            CoroutineScope(Dispatchers.IO).launch {
//                addFriendsUseCase(user)
//            }
//        }
//        rvAddFriends.layoutManager = LinearLayoutManager(this)
//        rvAddFriends.adapter = addFriendsAdapter
//        friendsViewModel.notFriends.observe(this){userList ->
//            addFriendsAdapter.updateUserList(userList)
//        }
//    }
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            android.R.id.home -> finish()
//        }
//        return true
//    }
}