package com.example.rawggames.model

import com.squareup.moshi.Json

data class LatestGame(
    @Json(name = "background_image")
    val backgroundImage: String? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "released")
    val released: String? = null,
)
