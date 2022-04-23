package com.example.rawggames.data.response.toprating

import com.squareup.moshi.Json

data class TopRating(
    @Json(name = "background_image")
    val backgroundImage: String? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "rating")
    val rating: Double? = null,
)
