package com.example.rawggames.model

import com.squareup.moshi.Json

data class SearchGameResponse(

    @Json(name = "results")
    val results: List<SearchedGame?>? = null,
)