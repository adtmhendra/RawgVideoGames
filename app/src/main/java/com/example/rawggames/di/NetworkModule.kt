package com.example.rawggames.di

import com.example.rawggames.networking.RawgApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // Moshi converter
    @Singleton
    @Provides
    fun provideMoshiConverter(): Moshi =
        Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi): Retrofit =
        Retrofit.Builder().apply {
            baseUrl(RawgApiService.BASE_URL)
            addConverterFactory(MoshiConverterFactory.create(moshi))
        }.build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): RawgApiService {
        val rawgApiService: RawgApiService by lazy {
            retrofit.create(RawgApiService::class.java)
        }
        return rawgApiService
    }
}