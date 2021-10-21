package com.gonzalo.airquality.web

import com.gonzalo.airquality.db.AirQuality
import com.gonzalo.airquality.model.geojsonmodel.aqi.AirQualityIndex
import com.gonzalo.airquality.model.geojsonmodel.aqi.pollutants.Pollutant
import com.gonzalo.airquality.model.geojsonmodel.aqi.pollutants.Pollutants
import com.gonzalo.airquality.web.apimodel.AirQualityApiResponse
import com.gonzalo.airquality.web.apimodel.Components
import javax.inject.Inject

class AirQualityBuilder @Inject constructor() {

    fun build(airQualityApiResponse: AirQualityApiResponse): AirQuality {
        val aqiList = mutableListOf<Int>()
        for (airQualityForecast in airQualityApiResponse.list) {
            val components = airQualityForecast.components
            val pollutants = Pollutants(
                listOf(
                    Pollutant.createPollutant(Components.no2ID, components.no2.toFloat()),
                    Pollutant.createPollutant(Components.o3ID, components.o3.toFloat()),
                    Pollutant.createPollutant(Components.so2ID, components.so2.toFloat()),
                    Pollutant.createPollutant(Components.pm2_5ID, components.pm25.toFloat()),
                    Pollutant.createPollutant(Components.pm10ID, components.pm10.toFloat())
                )
            )
            aqiList.add(AirQualityIndex(pollutants).value)
        }

        return AirQuality(airQualityApiResponse.coord.lat,
                          airQualityApiResponse.coord.lon,
                          aqiList)
    }
}