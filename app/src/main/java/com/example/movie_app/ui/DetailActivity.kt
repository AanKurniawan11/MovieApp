package com.example.movie_app.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.movie_app.databinding.ActivityDetailBinding
import com.example.movie_app.model.ResultX

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie = intent.getParcelableExtra<ResultX>("MOVIE_DATA")

        movie?.let {
            binding.titleTextView.text = it.title
            binding.overviewTextView.text = it.overview
            binding.tvRate.text = String.format("%.1f", movie.vote_average)
            binding.tvRelease.text = it.release_date.split("-")[0]
            binding.tvVote.text = "From " + it.vote_count.toString() + " users"

            Glide.with(this)
                .load("https://image.tmdb.org/t/p/original${it.backdrop_path}")
                .centerCrop()
                .into(binding.posterImageView)
        }

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnShare.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            val shareText = "Check out this movie: ${movie?.title}\n\nOverview: ${movie?.overview}"
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareText)
            startActivity(Intent.createChooser(shareIntent, "Share via"))
        }
    }
}
