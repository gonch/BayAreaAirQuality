package com.gonzalo.airquality.di

import com.gonzalo.airquality.web.AirQualityApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    private val BASE_URL = "https://api.openweathermap.org/"

    @Singleton
    @Provides
    fun provideCoroutineDispatcher() = Dispatchers.IO

    @Singleton
    @Provides
    fun provideApiService(): AirQualityApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(AirQualityApiService::class.java)
}