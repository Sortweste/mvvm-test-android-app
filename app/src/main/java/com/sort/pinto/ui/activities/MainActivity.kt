package com.sort.pinto.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.NavDestination
import androidx.navigation.ui.setupWithNavController
import com.sort.pinto.R
import com.sort.pinto.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.topAppBar)

        /*Preparing BottomNavMenu*/
        val navController = findNavController(R.id.fragment)
        binding.bottomNavigation.setupWithNavController(navController)
        navController.addOnDestinationChangedListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun componentsVisibility(status: Int){
        binding.bottomNavigation.visibility = status
        binding.topAppBar.visibility = status
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        when(destination.id){
            R.id.clientFragment, R.id.profileFragment, R.id.categoryFragment, R.id.salesFragment, R.id.reserveFragment -> componentsVisibility(
                View.VISIBLE)
            else -> componentsVisibility(View.GONE)
        }
    }

}