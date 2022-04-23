package com.example.rawggames.data.response.searchgame

import com.squareup.moshi.Json

data class SearchGameResponse(
    @Json(name = "results")
    val results: List<SearchGame?>? = null,
)