package com.example.rawggames.networking

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://api.rawg.io/api/"

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit: Retrofit = Retrofit.Builder().apply {
    addConverterFactory(MoshiConverterFactory.create(moshi))
    baseUrl(BASE_URL)
}.build()

object RawgApi {
    val retrofitService: RawgApiService by lazy { retrofit.create(RawgApiService::class.java) }
}