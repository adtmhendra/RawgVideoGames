package com.example.rawggames.domain.usecase

import com.example.rawggames.data.response.searchgame.SearchGameResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Query

interface SearchUseCase {
    fun getSearchGame(@Query("search") query: String): Observable<SearchGameResponse>
}