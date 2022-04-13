package com.example.rawggames.networking

object RawgApi {
    val retrofitService: RawgApiService by lazy { retrofit.create(RawgApiService::class.java) }
}