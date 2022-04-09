package com.example.rawggames.model

import com.squareup.moshi.Json

data class TopRating(
    @Json(name = "background_image")
    var backgroundImage: String? = null,

    @Json(name = "name")
    var name: String? = null,

    @Json(name = "rating")
    var rating: Double? = null
)
