package com.example.rawggames.di

import com.example.rawggames.data.repository.SearchRepository
import com.example.rawggames.data.source.RawgApiService
import com.example.rawggames.domain.interactor.SearchInteractor
import com.example.rawggames.domain.repository.ISearchRepository
import com.example.rawggames.domain.usecase.SearchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchModule {

    @Singleton
    @Provides
    fun provideISearchRepository(rawgApiService: RawgApiService): ISearchRepository =
        SearchRepository(rawgApiService)

    @Singleton
    @Provides
    fun provideSearchUseCase(iSearchRepository: ISearchRepository): SearchUseCase =
        SearchInteractor(iSearchRepository)
}