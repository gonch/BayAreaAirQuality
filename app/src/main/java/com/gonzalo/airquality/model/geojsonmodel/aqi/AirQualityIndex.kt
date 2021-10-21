package com.gonzalo.airquality.model.geojsonmodel.aqi

import com.gonzalo.airquality.model.geojsonmodel.aqi.pollutants.Pollutant
import com.gonzalo.airquality.model.geojsonmodel.aqi.pollutants.Pollutants

class AirQualityIndex(val pollutants: Pollutants) {

    enum class AQILevel {
        GOOD,
        MODERATE,
        UNHEALTHY_SENSITIVE,
        UNHEALTHY,
        VERY_UNHEALTHY,
        HAZARDOUS_LOW,
        HAZARDOUS_HIGH,
        N_A
    }

    private val aqiRangeGood = 0..50
    private val aqiRangeModerate = 51..100
    private val aqiRangeUnhealthySensitive = 101..150
    private val aqiRangeUnhealthy = 151..200
    private val aqiRangeVeryUnhealthy = 201..300
    private val aqiRangeHazardous1 = 301..400
    private val aqiRangeHazardous2 = 401..500

    val value = this.getAQIValue().toInt()

    private fun getAQIValue(): Float {
        var maxAQI = 0f
        for (pollutant in pollutants) {
            if (pollutant.pollutantLevelRange.range !=  Pollutant.rangeInvalid &&
                pollutant.pollutantLevelRange.aqiLevel != AQILevel.N_A) {
                val aqiRange = when (pollutant.pollutantLevelRange.aqiLevel) {
                    AQILevel.GOOD -> aqiRangeGood
                    AQILevel.MODERATE -> aqiRangeModerate
                    AQILevel.UNHEALTHY_SENSITIVE -> aqiRangeUnhealthySensitive
                    AQILevel.UNHEALTHY -> aqiRangeUnhealthy
                    AQILevel.VERY_UNHEALTHY -> aqiRangeVeryUnhealthy
                    AQILevel.HAZARDOUS_LOW -> aqiRangeHazardous1
                    AQILevel.HAZARDOUS_HIGH -> aqiRangeHazardous2
                    AQILevel.N_A -> aqiRangeHazardous2
                }

                val aqi = calculateAQI(aqiRange, pollutant)
                if (aqi > maxAQI) maxAQI = aqi
            }
        }
        return maxAQI
    }


    private fun calculateAQI(
        aqiRange: IntRange,
        pollutant: Pollutant
    ): Float {
        val iLow = aqiRange.first
        val iHigh = aqiRange.last

        val cLow = pollutant.pollutantLevelRange.range.start
        val cHigh = pollutant.pollutantLevelRange.range.endInclusive

        val c = pollutant.level

        return ((iHigh - iLow) / (cHigh - cLow)) * (c - cLow) + iLow
    }

}