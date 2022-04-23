package com.example.rawggames.data.repository

import com.example.rawggames.data.response.latestgame.LatestGameResponse
import com.example.rawggames.data.response.toprating.TopRatingResponse
import com.example.rawggames.data.source.RawgApiService
import com.example.rawggames.domain.repository.IHomeRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class HomeRepository @Inject constructor(private val rawgApiService: RawgApiService) :
    IHomeRepository {

    override fun getListTopRating(): Observable<TopRatingResponse> =
        rawgApiService.getListTopRating()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    override fun getLatestGame(): Observable<LatestGameResponse> =
        rawgApiService.getLatestGame()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}