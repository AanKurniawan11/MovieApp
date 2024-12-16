package com.example.movie_app.data

import com.example.movie_app.model.MoviePopularResponse
import com.example.movie_app.model.TopMovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {


    @GET("popular")
     fun getPopularMovies(
        @Query("api_key") apiKey: String,
    ): Call<MoviePopularResponse>

     @GET("top_rated")
     fun getTopMovies(
        @Query("api_key") apiKey: String,
    ): Call<TopMovieResponse>

}