package com.example.rawggames.domain.interactor

import com.example.rawggames.data.response.latestgame.LatestGameResponse
import com.example.rawggames.data.response.toprating.TopRatingResponse
import com.example.rawggames.domain.repository.IHomeRepository
import com.example.rawggames.domain.usecase.HomeUseCase
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class HomeInteractor @Inject constructor(private val iHomeRepository: IHomeRepository) :
    HomeUseCase {

    override fun getListTopRating(): Observable<TopRatingResponse> =
        iHomeRepository.getListTopRating().flatMap { Observable.just(it) }

    override fun getLatestGame(): Observable<LatestGameResponse> =
        iHomeRepository.getLatestGame().flatMap { Observable.just(it) }
}