package com.example.movie_app.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movie_app.CarouselAdapter
import com.example.movie_app.RatedAdapter
import com.example.movie_app.data.RetrofitClient
import com.example.movie_app.databinding.ActivityMainBinding
import com.example.movie_app.model.MoviePopularResponse
import com.example.movie_app.model.TopMovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var carouselAdapter: CarouselAdapter
    private lateinit var recycleAdapter: RatedAdapter
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    private var currentPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        carouselAdapter = CarouselAdapter()
        recycleAdapter = RatedAdapter { movie ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("MOVIE_DATA", movie)
            startActivity(intent)
        }

        binding.carousel.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = carouselAdapter
        }

        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2) // 2 kolom
            adapter = recycleAdapter
            setHasFixedSize(true)
        }

        binding.profileImage.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        getPopularMovies()
        getTopRatedMovies()

        handler = Handler()
        runnable = object : Runnable {
            override fun run() {
                val totalItemCount = carouselAdapter.itemCount
                if (totalItemCount > 0) {
                    currentPosition = (currentPosition + 1) % totalItemCount
                    binding.carousel.smoothScrollToPosition(currentPosition)
                    handler.postDelayed(this, 3000)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable, 3000)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }

    private fun getPopularMovies() {
        binding.progressBar.visibility = View.VISIBLE
        val apiKey = "3ed9cc9981534ab7fae4f418b27832df"
        RetrofitClient.apiService.getPopularMovies(apiKey)
            .enqueue(object : Callback<MoviePopularResponse> {
                override fun onResponse(
                    call: Call<MoviePopularResponse>,
                    response: Response<MoviePopularResponse>
                ) {
                    binding.progressBar.visibility = View.GONE
                    if (response.isSuccessful) {
                        carouselAdapter.updateMovies(response.body()?.results ?: emptyList())
                    } else {
                        binding.errorText.visibility = View.VISIBLE
                        binding.errorText.text = "Error: ${response.message()}"
                    }
                }

                override fun onFailure(call: Call<MoviePopularResponse>, t: Throwable) {
                    binding.progressBar.visibility = View.GONE
                    binding.errorText.visibility = View.VISIBLE
                    binding.errorText.text = "Failure: ${t.localizedMessage}"
                }
            })
    }

    private fun getTopRatedMovies() {
        val apiKey = "3ed9cc9981534ab7fae4f418b27832df"
        RetrofitClient.apiService.getTopMovies(apiKey)
            .enqueue(object : Callback<TopMovieResponse> {
                override fun onResponse(
                    call: Call<TopMovieResponse>,
                    response: Response<TopMovieResponse>
                ) {
                    if (response.isSuccessful) {
                        recycleAdapter.updateMovies(response.body()?.results ?: emptyList())
                    } else {
                        Log.e("MainActivity", "Error: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<TopMovieResponse>, t: Throwable) {
                    Log.e("MainActivity", "Failure: ${t.localizedMessage}")
                }
            })
    }
}
