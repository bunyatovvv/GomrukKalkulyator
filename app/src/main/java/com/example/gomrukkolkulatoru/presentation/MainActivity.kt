package com.example.gomrukkolkulatoru.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.gomrukkolkulatoru.R
import com.example.gomrukkolkulatoru.common.util.gone
import com.example.gomrukkolkulatoru.common.util.visible
import com.example.gomrukkolkulatoru.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHost =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHost.navController

        binding.bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener {
                controller, destination, arguments ->
            with(binding.bottomNavigationView){
                when(destination.id){
                    R.id.resultFragment -> gone()
                    else ->{
                        visible()
                    }
                }
            }
        }
    }
}