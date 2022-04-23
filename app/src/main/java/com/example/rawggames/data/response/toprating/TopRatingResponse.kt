package com.example.rawggames.data.response.toprating

import com.squareup.moshi.Json

data class TopRatingResponse(
    @Json(name = "results")
    val results: List<TopRating?>? = null,
)