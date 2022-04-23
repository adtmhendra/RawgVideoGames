package com.example.rawggames.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rawggames.data.response.searchgame.SearchGame
import com.example.rawggames.databinding.VerticalListSearchedGamesBinding

class SearchGameAdapter :
    ListAdapter<SearchGame, SearchGameAdapter.SearchGameViewHolder>(DiffCallback) {

    class SearchGameViewHolder(private val binding: VerticalListSearchedGamesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(searchedGame: SearchGame) {
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

    companion object DiffCallback : DiffUtil.ItemCallback<SearchGame>() {
        override fun areItemsTheSame(oldItem: SearchGame, newItem: SearchGame): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: SearchGame, newItem: SearchGame): Boolean {
            return oldItem.rating == newItem.rating
        }
    }
}