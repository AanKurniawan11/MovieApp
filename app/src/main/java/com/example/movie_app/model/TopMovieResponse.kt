package com.example.movie_app.model


data class TopMovieResponse(
    val page: Int,
    val results: List<ResultX>,
    val total_pages: Int,
    val total_results: Int
)