package com.example.rawggames.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rawggames.data.response.toprating.TopRating
import com.example.rawggames.databinding.HorizontalListTopRatingBinding

class TopRatingAdapter :
    ListAdapter<TopRating, TopRatingAdapter.TopRatingViewHolder>(DiffCallback) {

    class TopRatingViewHolder(private var binding: HorizontalListTopRatingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(topRating: TopRating) {
            binding.apply {
                this.topRating = topRating
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatingViewHolder {
        return TopRatingViewHolder(HorizontalListTopRatingBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: TopRatingViewHolder, position: Int) {
        val topRating = getItem(position)
        holder.bind(topRating)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<TopRating>() {
        override fun areItemsTheSame(oldItem: TopRating, newItem: TopRating): Boolean {
            return oldItem.backgroundImage == newItem.backgroundImage
        }

        override fun areContentsTheSame(oldItem: TopRating, newItem: TopRating): Boolean {
            return oldItem.name == newItem.name
        }
    }
}