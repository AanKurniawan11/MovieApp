package com.example.movie_app

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.example.movie_app.model.Result


class CarouselAdapter() : RecyclerView.Adapter<CarouselAdapter.ViewHolder>() {

    private val movies: MutableList<Result> = mutableListOf()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val ratingTextView: TextView? = view.findViewById(R.id.tv_rate)
        val titleTextView: TextView? = view.findViewById(R.id.tv_title)
        val voteTextView: TextView? = view.findViewById(R.id.tv_vote)
        val releaseTextView: TextView? = view.findViewById(R.id.tv_release)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_carousel, parent, false)
        return ViewHolder(view)


    }

    // Bind data to view holder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        val imageUrl = "https://image.tmdb.org/t/p/original${movie.backdrop_path}"

        // Load image using Glide
        Glide.with(holder.imageView.context)
            .load(imageUrl)
            .override(500, 281)
            .into(holder.imageView)

        holder.ratingTextView?.text = String.format("%.1f", movie.vote_average)
        holder.titleTextView?.text = movie.title
        holder.voteTextView?.text = "From " + movie.vote_count.toString() + " users"
        holder.releaseTextView?.text = movie.release_date.split("-")[0]


    }

    override fun getItemCount(): Int = movies.size

    // Update the movie list dynamically
    fun updateMovies(newMovies: List<Result>) {
        movies.clear()
        movies.addAll(newMovies)
        notifyDataSetChanged()
    }
}
