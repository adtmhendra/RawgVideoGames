package com.example.rawggames.di

import com.example.rawggames.data.constant.RestConstant
import com.example.rawggames.data.source.RawgApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
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
            baseUrl(RestConstant.RawgApi.BASE_URL)
            addConverterFactory(MoshiConverterFactory.create(moshi))
            addCallAdapterFactory(RxJava3CallAdapterFactory.create())
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