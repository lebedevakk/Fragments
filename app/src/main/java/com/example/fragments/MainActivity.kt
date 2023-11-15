package com.example.fragments


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.fragments.constants.MAIN
import com.example.fragments.databinding.ActivityMainBinding
import com.example.fragments.viewModels.CityModel


class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var navController: NavController
    private val cityViewModel: CityModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = Navigation.findNavController(this,R.id.nav_host_fragment)

        MAIN = this
    }
}