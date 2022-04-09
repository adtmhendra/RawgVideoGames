package com.example.rawggames.networking

import com.example.rawggames.model.TopRatingResponse
import retrofit2.Call
import retrofit2.http.GET

private const val API_KEY = "4d601705c8324648b378d5e18e98d78e"

interface RawgApiService {
    @GET("games?key=${API_KEY}&page_size=10&ordering=-rating&platforms=4&page=1")
    fun getListTopRating() : Call<TopRatingResponse>
}