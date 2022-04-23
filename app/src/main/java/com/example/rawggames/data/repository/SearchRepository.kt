package com.example.rawggames.data.repository

import com.example.rawggames.data.response.searchgame.SearchGameResponse
import com.example.rawggames.data.source.RawgApiService
import com.example.rawggames.domain.repository.ISearchRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class SearchRepository @Inject constructor(private val rawgApiService: RawgApiService) :
    ISearchRepository {

    override fun getSearchGame(query: String): Observable<SearchGameResponse> =
        rawgApiService.getSearchGame(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}