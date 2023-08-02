package com.example.definexcase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.definexcase.databinding.ActivityMainBinding
import com.example.definexcase.view.book.BookFragment
import com.example.definexcase.view.home.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setNavigation()
    }

    private fun setNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navMainFragment) as NavHostFragment
        navController = navHostFragment.navController

        binding.bottomNavigation.setOnItemSelectedListener(object :
            NavigationView.OnNavigationItemSelectedListener,
            NavigationBarView.OnItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.title) {
                    "Discover" -> {
                        navHostFragment.findNavController().navigate(R.id.homeFragment)
                    }
                    "Book" -> {
                        navHostFragment.findNavController().navigate(R.id.bookFragment)
                    }
                    "Basket" -> {
                        navHostFragment.findNavController().navigate(R.id.cartFragment)
                    }
                    "Favorites" -> {
                        navHostFragment.findNavController().navigate(R.id.favoritesFragment)
                    }
                    "Profil" -> {
                        navHostFragment.findNavController().navigate(R.id.profileFragment)
                    }
                }
                return true
            }
        })
    }


}