package com.example.movie_app.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie_app.ProfileAdapter
import com.example.movie_app.R
import com.example.movie_app.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBacks.setOnClickListener {
            finish()
        }

        binding.rvProfile.apply {
            layoutManager = LinearLayoutManager(this@ProfileActivity, LinearLayoutManager.VERTICAL, false)
            adapter = ProfileAdapter()
        }
    }
}