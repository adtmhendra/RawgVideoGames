package com.example.rawggames.data.response.latestgame

import com.squareup.moshi.Json

data class LatestGameResponse(
    @Json(name = "results")
    val results: List<LatestGame?>? = null,
)
