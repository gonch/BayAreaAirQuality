package com.gonzalo.airquality.web

import com.gonzalo.airquality.web.apimodel.AirQualityApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AirQualityApiService {

    @GET("data/2.5/air_pollution/forecast")
    suspend fun getAirQualityLocation(@Query("lat") x: Float,
                              @Query("lon") y: Float,
                              @Query("appid") appid: String) : AirQualityApiResponse //TODO add appid as constant in this class, not a parameter

}

