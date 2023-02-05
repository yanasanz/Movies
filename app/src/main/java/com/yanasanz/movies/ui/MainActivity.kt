package com.yanasanz.movies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.yanasanz.movies.R
import com.yanasanz.movies.databinding.ActivityMainBinding
import com.yanasanz.movies.databinding.ActivityMainBinding.inflate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment

        val navController = navHostFragment.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.feedFragment, R.id.favouriteFilmsFragment -> {
                    binding.popular.visibility = VISIBLE
                    binding.favourite.visibility = VISIBLE
                }
                else -> {
                    binding.popular.visibility = GONE
                    binding.favourite.visibility = GONE
                }
            }
        }

        appBarConfiguration = AppBarConfiguration(navController.graph)

        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.popular.isChecked = true

        binding.popular.setOnClickListener{
            binding.favourite.isChecked = false
            binding.popular.isChecked = true
            navController.navigate(R.id.feedFragment)
        }
        binding.favourite.setOnClickListener{
            binding.popular.isChecked = false
            binding.favourite.isChecked = true
            navController.navigate(R.id.favouriteFilmsFragment)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.navHostFragment)
        return navController.navigateUp(appBarConfiguration)
        // || super.onSupportNavigateUp()
    }
}