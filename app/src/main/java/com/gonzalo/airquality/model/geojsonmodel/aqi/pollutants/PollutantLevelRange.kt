package com.gonzalo.airquality.model.geojsonmodel.aqi.pollutants

import com.gonzalo.airquality.model.geojsonmodel.aqi.AirQualityIndex

data class PollutantLevelRange (val range : ClosedFloatingPointRange<Float>, val aqiLevel: AirQualityIndex.AQILevel)