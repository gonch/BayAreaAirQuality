package com.gonzalo.airquality.repositories

import android.util.Log
import com.gonzalo.airquality.data.CoordinatesData
import com.gonzalo.airquality.db.AirQualityDao
import com.gonzalo.airquality.model.AirQualityBatch
import com.gonzalo.airquality.web.AirQualityApiService
import com.gonzalo.airquality.web.AirQualityBuilder
import com.gonzalo.airquality.web.ResponseWrapper
import kotlinx.coroutines.*
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AirQualityRepository @Inject constructor(private val airQualityDao: AirQualityDao,
                                               private val airQualityBuilder: AirQualityBuilder,
                                               private val apiService: AirQualityApiService,
                                               private val dispatcher : CoroutineDispatcher) {

    private val API_KEY = "bb28978ccf0b4b37f41554d06d5df2bb"

    private var lastUpdateFromAPI : Date? = null

    private val coordinates = CoordinatesData.BAY_AREA_TOWNS

    suspend fun fetchAirQualities(): AirQualityBatch = coroutineScope {
        var airQualities = AirQualityBatch(listOf())
        if (hasDataExpired()) {
            coordinates.map {
                async {
                    val wrappedAirQualityResponse = fetchAirQualityEndpoint(dispatcher) {
                        apiService.getAirQualityLocation(it.lat,it.lon,API_KEY)
                    }
                    when (wrappedAirQualityResponse) {
                        is ResponseWrapper.Success -> {
                            val airQuality = airQualityBuilder.build(wrappedAirQualityResponse.value)
                            if (airQuality.aqiForecast.isEmpty()) {
                                Log.e("repo", "wtf")
                            }
                            airQualityDao.save(airQuality)
                            airQualities.add(airQuality)
                        }
                        is ResponseWrapper.Error -> {
                            airQualities.error = wrappedAirQualityResponse
                        }
                    }
                }
            }.awaitAll().run {
                lastUpdateFromAPI = Date()
            }
        } else {
            airQualities = AirQualityBatch(airQualityDao.getAll().toMutableList())
        }

        airQualities
    }

    private suspend fun <T> fetchAirQualityEndpoint(dispatcher: CoroutineDispatcher,
                                                    apiCall : suspend () -> T): ResponseWrapper<T> {

        return withContext(dispatcher) {
            try {
                ResponseWrapper.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                if (throwable.message == null) {
                    ResponseWrapper.Error("Unknown Error")
                } else {
                    ResponseWrapper.Error(throwable.message!!)
                }
            }
        }
    }

    private fun hasDataExpired(): Boolean {
        return if (lastUpdateFromAPI == null) {
            true
        } else {
            val diffInMillis : Long = lastUpdateFromAPI!!.time - Date().time
            (TimeUnit.MILLISECONDS.toHours(diffInMillis) > 1)
        }
    }
}