package com.learning.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.learning.R
import com.learning.databinding.ActivityMainBinding
import com.learning.databinding.ActivityNevHomeBinding
import com.learning.utills.SharedPrf
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NevHomeAct : AppCompatActivity() {
    lateinit var binding: ActivityNevHomeBinding
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_nev_home)
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment? ?: return
        navController = host.navController
    }

}