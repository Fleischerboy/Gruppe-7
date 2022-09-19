package org.example.kotlin.android.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import org.example.kotlin.android.app.data.UserPreferences
import org.example.kotlin.android.app.databinding.ActivityMainBinding
import org.example.kotlin.android.app.ui.auth.AuthActivity
import org.example.kotlin.android.app.ui.home.HomeActivity
import org.example.kotlin.android.app.ui.startNewActivity


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root);
        val userPreferences = UserPreferences(this)

        userPreferences.getAccessToken.asLiveData().observe(this, Observer {
            val activity = if(it == null) AuthActivity::class.java else HomeActivity::class.java
            startNewActivity(activity);
        })













    }




}