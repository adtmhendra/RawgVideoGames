package com.example.rawggames.networking

import com.example.rawggames.model.LatestGameResponse
import com.example.rawggames.model.TopRatingResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val API_KEY = "4d601705c8324648b378d5e18e98d78e"
const val BASE_URL = "https://api.rawg.io/api/"
val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
val retrofit: Retrofit = Retrofit.Builder().apply {
    baseUrl(BASE_URL)
    addConverterFactory(MoshiConverterFactory.create(moshi))
}.build()

interface RawgApiService {
    @GET("games?key=${API_KEY}&page_size=10&ordering=-rating&platforms=4&page=1")
    suspend fun getListTopRating(): Response<TopRatingResponse>

    @GET("games?key=${API_KEY}&page_size=10&ordering=-released&platforms=4&page=1&dates=2021-12-01,2021-12-31")
    suspend fun getLatestGame(): Response<LatestGameResponse>
}