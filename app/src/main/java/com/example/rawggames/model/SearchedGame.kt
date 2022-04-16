package com.example.rawggames.model

import com.squareup.moshi.Json

data class SearchedGame(

    @Json(name = "background_image")
    val backGroundImage: String? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "rating")
    val rating: Double? = null,
)
