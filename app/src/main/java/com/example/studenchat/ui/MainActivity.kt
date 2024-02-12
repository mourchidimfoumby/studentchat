package com.example.studenchat.ui
import AuthenticationViewModel
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.studenchat.R
import com.example.studenchat.authentication.AuthenticationActivity
import com.example.studenchat.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if(Firebase.auth.currentUser == null)
            Intent(this, AuthenticationActivity::class.java).also {
            startActivity(it)
            finish()
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
