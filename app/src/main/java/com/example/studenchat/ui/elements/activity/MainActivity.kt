package com.example.studenchat.ui.elements.activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.studenchat.R
import com.example.studenchat.authentication.domain.IsLoggedInUseCase
import com.example.studenchat.authentication.ui.AuthenticationActivity
import com.example.studenchat.databinding.ActivityMainBinding
import com.example.studenchat.ui.stateholders.dialog.DisconnectionDialogFragment
import com.example.studenchat.ui.elements.activity.fragment.ConversationFragment
import com.example.studenchat.ui.elements.activity.fragment.AppsFragment
import com.example.studenchat.ui.elements.fragment.FriendsFragment
import com.example.studenchat.ui.stateholders.viewmodel.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(){
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    private lateinit var btmNavigationView: BottomNavigationView
    private val mainViewModel : MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authenticationViewModel.logged.observe(this) { isLogged ->
            if (!isLogged) {
                Intent(this, AuthenticationActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        btmNavigationView = binding.btmNavigationView
        btmNavigationView.setOnItemSelectedListener{itemMenu ->
            when(itemMenu.itemId){
                R.id.nav_item_conversation -> {
                    supportActionBar!!.title = getString(R.string.conversation)
                    setCurrentFragment(ConversationFragment())
                }
                R.id.nav_item_friends -> {
                    supportActionBar!!.title = getString(R.string.friends)
                    setCurrentFragment(FriendsFragment())
                }
                R.id.nav_item_apps -> {
                    supportActionBar!!.title = getString(R.string.apps)
                    setCurrentFragment(AppsFragment())
                }
            }
            true
        }
        setCurrentFragment(ConversationFragment(), getString(R.string.conversation))
    }

    private fun setCurrentFragment(fragment: Fragment, title: String? = null){
        if(title != null) supportActionBar!!.title = title
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_layout_main, fragment)
            commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.app_bar_item_account -> {
                Intent(this, AccountActivity::class.java).also {
                    startActivity(it)
                }
            }
            R.id.app_bar_item_logout -> DisconnectionDialogFragment().show(supportFragmentManager, null)
        }
        return true
    }
}
