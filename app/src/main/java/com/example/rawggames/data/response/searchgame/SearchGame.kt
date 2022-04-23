package com.example.rawggames.data.response.searchgame

import com.squareup.moshi.Json

data class SearchGame(
    @Json(name = "background_image")
    val backGroundImage: String? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "rating")
    val rating: Double? = null,
)
