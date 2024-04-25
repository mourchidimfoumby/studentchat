package com.example.studentchat

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.authentication.domain.IsLoggedInUseCase
import com.example.authentication.ui.AuthenticationActivity
import com.example.studentchat.databinding.ActivityMainBinding
import com.example.studentchat.sharedpreferences.SharedPreferencesName
import com.example.studentchat.sharedpreferences.SharedPreferencesTag
import com.example.studentchat.sharedpreferences.SharedPreferencesUseCase
import com.example.ui.conversation.ConversationFragment
import com.example.ui.friends.FriendsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
    private lateinit var btmNavigationView: BottomNavigationView
    private val isLoggedInUseCase: IsLoggedInUseCase by inject()
    private val sharedPreferencesUseCase: SharedPreferencesUseCase by inject {
        parametersOf(SharedPreferencesName.APP.name)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferencesUseCase.putBoolean(SharedPreferencesTag.APP_IS_LAUNCHED.name, true)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        btmNavigationView = binding.btmNavigationView
        if(isLoggedInUseCase()){
            btmNavigationView.setOnItemSelectedListener { itemMenu ->
                when (itemMenu.itemId) {
                    R.id.nav_item_conversation -> setCurrentFragment(ConversationFragment())
                    R.id.nav_item_friends -> setCurrentFragment(FriendsFragment())
                }
                true
            }
            setCurrentFragment(ConversationFragment())
        }
        else {
            launchActivity(
                activityClass = AuthenticationActivity::class.java,
                contextFeatures = {
                    finish()
                }
            )
            sharedPreferencesUseCase.putBoolean(SharedPreferencesTag.APP_IS_LAUNCHED.name, false)
        }
    }

    private fun setCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().also {
            it.replace(R.id.frame_layout_main, fragment)
            it.commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.app_bar_item_logout -> DisconnectionDialogFragment().show(supportFragmentManager, null)
        }
        return true
    }
}
