package com.example.movie_app

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movie_app.databinding.ItemProfileBinding
import com.example.movie_app.model.ProfileData

class ProfileAdapter : RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {

    private val items = listOf(
        ProfileData(R.drawable.ic_email, "Aan@gmail.com"),
        ProfileData(R.drawable.ic_ig, "krnwnz.aan"),
        ProfileData(R.drawable.ic_linkdn, "Aan Kurniawan"),
    )

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProfileViewHolder {
        val binding = ItemProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfileViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ProfileViewHolder(private val binding: ItemProfileBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ProfileData) {
            binding.imgLogo.setImageResource(item.imageResId)
            binding.title.text = item.title
        }
    }
}