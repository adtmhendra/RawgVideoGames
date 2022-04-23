package com.example.rawggames.di

import com.example.rawggames.data.repository.HomeRepository
import com.example.rawggames.data.source.RawgApiService
import com.example.rawggames.domain.interactor.HomeInteractor
import com.example.rawggames.domain.repository.IHomeRepository
import com.example.rawggames.domain.usecase.HomeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Singleton
    @Provides
    fun provideIHomeRepository(rawgApiService: RawgApiService): IHomeRepository =
        HomeRepository(rawgApiService)

    @Singleton
    @Provides
    fun provideHomeUseCase(iHomeRepository: IHomeRepository): HomeUseCase =
        HomeInteractor(iHomeRepository)
}