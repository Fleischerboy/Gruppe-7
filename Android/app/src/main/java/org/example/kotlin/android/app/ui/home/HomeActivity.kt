package org.example.kotlin.android.app.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import org.example.kotlin.android.app.R
import org.example.kotlin.android.app.databinding.ActivityHomeBinding
import org.example.kotlin.android.app.ui.auth.AuthActivity
import org.example.kotlin.android.app.ui.home.productOverview.ProductActivity
import org.example.kotlin.android.app.ui.home.profile.ProfileViewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding;

    private val viewModel by viewModels<ProfileViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerViewHome) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.exploreFragment, R.id.sellFragment, R.id.inboxFragment, R.id.profileFragment))
        setupActionBarWithNavController(navController, appBarConfiguration);
        binding.bottomNavigationView.setupWithNavController(navController);



    }

    fun sendProductIntent(productId : Int){
        val intent = Intent(baseContext, ProductActivity::class.java)
        intent.putExtra("EXTRA_PRODUCT_ID", productId)
        startActivity(intent)
    }






}