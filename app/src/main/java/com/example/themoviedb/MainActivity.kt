package com.example.themoviedb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.themoviedb.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewPageAdapter: ViewPageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewPageAdapter = ViewPageAdapter(supportFragmentManager, lifecycle)
        binding.viewPager.adapter = viewPageAdapter


        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.tab_layout)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_movie -> {
                    binding.viewPager.currentItem = 0
                    true
                }
                R.id.menu_tv -> {
                    binding.viewPager.currentItem = 1
                    true
                }
                R.id.menu_profile -> {
                    binding.viewPager.currentItem = 2
                    true
                }
                else -> false
            }
        }
    }
}
