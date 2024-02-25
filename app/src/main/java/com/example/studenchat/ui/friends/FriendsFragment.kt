package com.example.studenchat.ui.friends



//class FriendsFragment: Fragment(R.layout.fragment_friends) {
//    private lateinit var binding : FragmentFriendsBinding
//    private lateinit var recyclerViewFriends: RecyclerView
//    private lateinit var txtViewEmptyFriends: TextView
//    private lateinit var floatingButtonFriends: FloatingActionButton
//    private lateinit var progressBar: ProgressBar
//    private lateinit var friendsAdapter: FriendsAdapter
//    private val friendsViewModel: FriendsViewModel by viewModels()
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        binding = FragmentFriendsBinding.bind(view)
//        recyclerViewFriends = binding.rvFriends
//        txtViewEmptyFriends = binding.txtViewEmptyFriends
//        floatingButtonFriends = binding.floatingButtonFriends
//        progressBar = binding.progressBarFriends
//
//        progressBar.isVisible = true
//        friendsAdapter = FriendsAdapter(emptyList()) {friends ->
//            requireContext().clickToast("Click sur ${friends.firstname}")
//        }
//        recyclerViewFriends.adapter = friendsAdapter
//        recyclerViewFriends.layoutManager = LinearLayoutManager(context)
//
//        friendsViewModel.friends.observe(viewLifecycleOwner){ friendsList ->
//            progressBar.isVisible = false
//            friendsAdapter.updateFriendsList(friendsList)
//            if(friendsAdapter.itemCount > 0){
//                txtViewEmptyFriends.isVisible = false
//                recyclerViewFriends.isVisible = true
//            }
//            else{
//                txtViewEmptyFriends.isVisible = true
//                recyclerViewFriends.isVisible = false
//            }
//        }
//        floatingButtonFriends.setOnClickListener {
//            Intent(requireContext(), AddFriendsActivity::class.java).also {
//                startActivity(it)
//            }
//        }
//    }
//}