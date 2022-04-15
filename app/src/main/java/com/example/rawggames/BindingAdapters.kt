package com.example.rawggames

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rawggames.adapter.LatestGameAdapter
import com.example.rawggames.adapter.TopRatingAdapter
import com.example.rawggames.model.LatestGame
import com.example.rawggames.model.State
import com.example.rawggames.model.TopRating
import com.google.android.material.progressindicator.CircularProgressIndicator

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
    if (state == State.FAILED) {
        imageView.visibility = View.VISIBLE
        imageView.setImageResource(R.drawable.ic_connection_error)
    }
}

@BindingAdapter("textViewState")
fun bindTextViewState(textView: TextView, state: State) {
    if (state == State.LOADING || state == State.FAILED) textView.visibility = View.GONE
    else textView.visibility = View.VISIBLE
}

@BindingAdapter("circularProgressBarState")
fun bindCircularProgressBarState(
    circularProgressIndicator: CircularProgressIndicator,
    state: State,
) {
    if (state != State.LOADING) circularProgressIndicator.visibility = View.GONE
}