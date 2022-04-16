package com.example.rawggames.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rawggames.databinding.VerticalListSearchedGamesBinding
import com.example.rawggames.model.SearchedGame

class SearchGameAdapter :
    ListAdapter<SearchedGame, SearchGameAdapter.SearchGameViewHolder>(DiffCallback) {

    class SearchGameViewHolder(private val binding: VerticalListSearchedGamesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(searchedGame: SearchedGame) {
            binding.apply {
                this.searchedGame = searchedGame
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchGameViewHolder {
        return SearchGameViewHolder(VerticalListSearchedGamesBinding.inflate(LayoutInflater.from(
            parent.context)))
    }

    override fun onBindViewHolder(holder: SearchGameViewHolder, position: Int) {
        val searchedGame = getItem(position)
        holder.bindItem(searchedGame)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<SearchedGame>() {
        override fun areItemsTheSame(oldItem: SearchedGame, newItem: SearchedGame): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: SearchedGame, newItem: SearchedGame): Boolean {
            return oldItem.rating == newItem.rating
        }
    }
}