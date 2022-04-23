package com.example.rawggames.domain.usecase

import com.example.rawggames.data.response.latestgame.LatestGameResponse
import com.example.rawggames.data.response.toprating.TopRatingResponse
import io.reactivex.rxjava3.core.Observable

interface HomeUseCase {
    fun getListTopRating(): Observable<TopRatingResponse>
    fun getLatestGame(): Observable<LatestGameResponse>
}