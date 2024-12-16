package com.example.movie_app.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.movie_app.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

         Handler (Looper.getMainLooper()).postDelayed({
            goToLogin()
        },2000)
    }
    private fun goToLogin(){
        Intent(this, MainActivity::class.java).also {
            startActivity(it)
            finish()
        }
    }

    }
