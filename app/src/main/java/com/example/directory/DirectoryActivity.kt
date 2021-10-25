package com.example.directory

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.directory.databinding.ActivityDirectoryBinding

class DirectoryActivity : AppCompatActivity() {
    private var binding: ActivityDirectoryBinding? = null
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDirectoryBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }

        navController = Navigation.findNavController(this, R.id.navHostFragment)
        binding?.let {
            it.directoryBottomNav.setupWithNavController(navController)
        }
        setupActionBarWithNavController(navController)

    }
}