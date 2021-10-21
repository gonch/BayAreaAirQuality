package com.gonzalo.airquality.model.geojsonmodel.aqi.pollutants

import com.gonzalo.airquality.web.apimodel.Components.Companion.coID
import com.gonzalo.airquality.web.apimodel.Components.Companion.no2ID
import com.gonzalo.airquality.web.apimodel.Components.Companion.o3ID
import com.gonzalo.airquality.web.apimodel.Components.Companion.pm10ID
import com.gonzalo.airquality.web.apimodel.Components.Companion.pm2_5ID
import com.gonzalo.airquality.web.apimodel.Components.Companion.so2ID
import com.gonzalo.airquality.model.geojsonmodel.aqi.AirQualityIndex

abstract class Pollutant(val level: Float) {

    protected abstract val rangeGood : ClosedFloatingPointRange<Float>
    protected abstract val rangeModerate : ClosedFloatingPointRange<Float>
    protected abstract val rangeUnhealthySensitive : ClosedFloatingPointRange<Float>
    protected abstract val rangeUnHealthy : ClosedFloatingPointRange<Float>
    protected abstract val rangeVeryUnHealthy : ClosedFloatingPointRange<Float>
    protected abstract val rangeHazardousLow : ClosedFloatingPointRange<Float>
    protected abstract val rangeHazardousHigh : ClosedFloatingPointRange<Float>

    companion object {
        val rangeInvalid = -1f..-1f

        fun createPollutant(type: String, level : Float) =
            when (type) {
                coID -> CO(level)
                no2ID -> NO2(level)
                o3ID -> O3(level)
                so2ID -> SO2(level)
                pm2_5ID -> PM2Point5(level)
                pm10ID -> PM10(level)
                else -> throw Exception("I don't know how to deal with pollutant type.")
            }
    }

    val pollutantLevelRange by lazy {
        when (level) {
            in rangeGood -> PollutantLevelRange(rangeGood, AirQualityIndex.AQILevel.GOOD)
            in rangeModerate -> PollutantLevelRange(rangeModerate, AirQualityIndex.AQILevel.MODERATE)
            in rangeUnhealthySensitive -> PollutantLevelRange(rangeUnhealthySensitive, AirQualityIndex.AQILevel.UNHEALTHY_SENSITIVE)
            in rangeUnHealthy -> PollutantLevelRange(rangeUnHealthy, AirQualityIndex.AQILevel.UNHEALTHY)
            in rangeVeryUnHealthy -> PollutantLevelRange(rangeVeryUnHealthy, AirQualityIndex.AQILevel.VERY_UNHEALTHY)
            in rangeHazardousLow -> PollutantLevelRange(rangeHazardousLow, AirQualityIndex.AQILevel.HAZARDOUS_LOW)
            in rangeHazardousHigh -> PollutantLevelRange(rangeHazardousHigh, AirQualityIndex.AQILevel.HAZARDOUS_HIGH)
            else -> PollutantLevelRange(rangeInvalid, AirQualityIndex.AQILevel.N_A)
        }
    }
}