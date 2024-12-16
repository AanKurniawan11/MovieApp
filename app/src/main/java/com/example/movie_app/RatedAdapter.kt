package com.example.movie_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movie_app.model.ResultX

class RatedAdapter(private val onItemClick: (ResultX) -> Unit) : RecyclerView.Adapter<RatedAdapter.ViewHolder>() {

    private val movies: MutableList<ResultX> = mutableListOf()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.poster)
        val releaseTextView: TextView = view.findViewById(R.id.release)
        val titleTextView: TextView = view.findViewById(R.id.titles)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_toprated, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        val imageUrl = "https://image.tmdb.org/t/p/original${movie.poster_path}"

        Glide.with(holder.imageView.context)
            .load(imageUrl)
            .centerCrop()
            .into(holder.imageView)

        holder.titleTextView.text = movie.title
        holder.releaseTextView.text = movie.release_date.split("-")[0]

        holder.itemView.setOnClickListener {
            onItemClick(movie)
        }
    }

    override fun getItemCount(): Int = movies.size

    fun updateMovies(newMovies: List<ResultX>) {
        movies.clear()
        movies.addAll(newMovies)
        notifyDataSetChanged()
    }
}
