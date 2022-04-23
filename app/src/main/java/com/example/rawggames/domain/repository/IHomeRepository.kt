package com.example.rawggames.domain.repository

import com.example.rawggames.data.response.latestgame.LatestGameResponse
import com.example.rawggames.data.response.toprating.TopRatingResponse
import io.reactivex.rxjava3.core.Observable

interface IHomeRepository {
    fun getListTopRating(): Observable<TopRatingResponse>
    fun getLatestGame(): Observable<LatestGameResponse>
}