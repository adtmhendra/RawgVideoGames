package com.example.rawggames

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rawggames.adapter.TopRatingAdapter
import com.example.rawggames.model.TopRating

@BindingAdapter("listTopRating")
fun bindRecyclerView(recyclerView: RecyclerView, listTopRating: List<TopRating>?) {
    val adapter = recyclerView.adapter as TopRatingAdapter
    adapter.submitList(listTopRating)
}

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imageUrl: String?) {
    imageUrl?.let {
        val imgUrl = imageUrl.toUri().buildUpon().scheme("https").build()
        imageView.load(imgUrl) {
            placeholder(R.drawable.ic_connection_error)
            error(R.drawable.ic_broken_image)
        }
    }
}