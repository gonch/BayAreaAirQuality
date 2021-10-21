package com.gonzalo.airquality.model.geojsonmodel.aqi

import com.gonzalo.airquality.model.geojsonmodel.aqi.pollutants.PM2Point5
import com.gonzalo.airquality.model.geojsonmodel.aqi.pollutants.Pollutants
import junit.framework.TestCase

class AirQualityApiResponseIndexTest : TestCase() {

    fun testComputePollutantsPM2Point5() {
        val pollutant = PM2Point5(26.4f)
        val airQualityIndex = AirQualityIndex(Pollutants(listOf(pollutant)))
        assertEquals(81, airQualityIndex.value)
    }

    fun testComputePollutantsPM210() {
        val pollutant = PM2Point5(1f)
        val airQualityIndex = AirQualityIndex(Pollutants(listOf(pollutant)))
        assertEquals(4 ,airQualityIndex.value)
    }

    fun testComputePollutantsO3() {
        val pollutant = PM2Point5(78f)
        val airQualityIndex = AirQualityIndex(Pollutants(listOf(pollutant)))
        assertEquals(162 ,airQualityIndex.value)
    }
}