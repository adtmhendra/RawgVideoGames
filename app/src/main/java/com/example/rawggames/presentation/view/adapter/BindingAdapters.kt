package com.example.rawggames.presentation.view.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rawggames.R
import com.example.rawggames.data.response.latestgame.LatestGame
import com.example.rawggames.data.response.searchgame.SearchGame
import com.example.rawggames.data.response.toprating.TopRating
import com.example.rawggames.presentation.state.State
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("listTopRating")
fun bindRecyclerViewTopRating(recyclerView: RecyclerView, listTopRating: List<TopRating>?) {
    val adapter = recyclerView.adapter as TopRatingAdapter
    adapter.submitList(listTopRating)
}

@BindingAdapter("listLatestGame")
fun bindRecyclerViewLatestGame(recyclerView: RecyclerView, listLatestGame: List<LatestGame>?) {
    val adapter = recyclerView.adapter as LatestGameAdapter
    adapter.submitList(listLatestGame)
}

@BindingAdapter("listSearchedGames")
fun bindRecyclerViewSearchedGames(recyclerView: RecyclerView, listLatestGame: List<SearchGame>?) {
    val adapter = recyclerView.adapter as SearchGameAdapter
    adapter.submitList(listLatestGame)
}

@BindingAdapter("imageUrl")
fun bindTextViewState(imageView: ImageView, imageUrl: String?) {
    imageUrl?.let {
        val imgUrl = imageUrl.toUri().buildUpon().scheme("https").build()
        imageView.load(imgUrl) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}

@BindingAdapter("imageViewState")
fun bindImageViewState(imageView: ImageView, state: State) {
    when (state) {
        State.FAILED -> {
            imageView.visibility = View.VISIBLE
            imageView.setImageResource(R.drawable.ic_empty_data)
        }
        State.ERROR -> {
            imageView.visibility = View.VISIBLE
            imageView.setImageResource(R.drawable.ic_connection_error)
        }
        else -> imageView.visibility = View.GONE
    }
}

@BindingAdapter("textViewState")
fun bindTextViewState(textView: TextView, state: State) {
    if (state == State.LOADING || state == State.ERROR) textView.visibility = View.GONE
    else textView.visibility = View.VISIBLE
}

@BindingAdapter("circularProgressBarState")
fun bindCircularProgressBarState(
    circularProgressIndicator: CircularProgressIndicator,
    state: State,
) {
    if (state == State.LOADING) circularProgressIndicator.visibility = View.VISIBLE
    else circularProgressIndicator.visibility = View.GONE
}

@BindingAdapter("searchBarState")
fun searchBarState(textInputLayout: TextInputLayout, state: State) {
    if (state == State.ERROR) textInputLayout.visibility = View.GONE
    else textInputLayout.visibility = View.VISIBLE
}