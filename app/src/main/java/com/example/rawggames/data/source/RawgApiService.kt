package com.example.rawggames.data.source

import com.example.rawggames.data.constant.RestConstant
import com.example.rawggames.data.response.latestgame.LatestGameResponse
import com.example.rawggames.data.response.searchgame.SearchGameResponse
import com.example.rawggames.data.response.toprating.TopRatingResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RawgApiService {

    @GET(RestConstant.RawgApi.listTopRating)
    fun getListTopRating(): Observable<TopRatingResponse>

    @GET(RestConstant.RawgApi.latestGame)
    fun getLatestGame(): Observable<LatestGameResponse>

    @GET(RestConstant.RawgApi.searchedGame)
    fun getSearchGame(@Query("search") query: String): Observable<SearchGameResponse>
}