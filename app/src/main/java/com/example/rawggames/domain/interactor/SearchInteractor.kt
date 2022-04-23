package com.example.rawggames.domain.interactor

import com.example.rawggames.data.response.searchgame.SearchGameResponse
import com.example.rawggames.domain.repository.ISearchRepository
import com.example.rawggames.domain.usecase.SearchUseCase
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class SearchInteractor @Inject constructor(private val iSearchRepository: ISearchRepository) :
    SearchUseCase {

    override fun getSearchGame(query: String): Observable<SearchGameResponse> =
        iSearchRepository.getSearchGame(query).flatMap { Observable.just(it) }
}