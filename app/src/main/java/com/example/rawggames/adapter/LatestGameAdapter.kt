package com.example.rawggames.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rawggames.databinding.HorizontalListLatestGameBinding
import com.example.rawggames.model.LatestGame

class LatestGameAdapter :
    ListAdapter<LatestGame, LatestGameAdapter.LatestGameViewHolder>(DiffCallback) {

    class LatestGameViewHolder(private val binding: HorizontalListLatestGameBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(latestGame: LatestGame) {
            binding.apply {
                this.latestGame = latestGame
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestGameViewHolder {
        return LatestGameViewHolder(HorizontalListLatestGameBinding.inflate(LayoutInflater.from(
            parent.context)))
    }

    override fun onBindViewHolder(holder: LatestGameViewHolder, position: Int) {
        val latestGame = getItem(position)
        holder.bindItem(latestGame)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<LatestGame>() {
        override fun areItemsTheSame(oldItem: LatestGame, newItem: LatestGame): Boolean {
            return oldItem.backgroundImage == newItem.backgroundImage
        }

        override fun areContentsTheSame(oldItem: LatestGame, newItem: LatestGame): Boolean {
            return oldItem.name == newItem.name
        }
    }

}