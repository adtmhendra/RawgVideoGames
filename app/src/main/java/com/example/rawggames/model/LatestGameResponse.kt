package com.example.rawggames.model

import com.squareup.moshi.Json

data class LatestGameResponse(
    @Json(name = "results")
    val results: List<LatestGame?>? = null,
)
