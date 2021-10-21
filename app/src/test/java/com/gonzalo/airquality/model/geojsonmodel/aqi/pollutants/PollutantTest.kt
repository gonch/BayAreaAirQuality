package com.gonzalo.airquality.model.geojsonmodel.aqi.pollutants

import com.gonzalo.airquality.web.apimodel.Components.Companion.coID
import junit.framework.TestCase

import com.gonzalo.airquality.web.apimodel.Components.Companion.no2ID
import com.gonzalo.airquality.web.apimodel.Components.Companion.o3ID
import com.gonzalo.airquality.web.apimodel.Components.Companion.pm10ID
import com.gonzalo.airquality.web.apimodel.Components.Companion.pm2_5ID
import com.gonzalo.airquality.web.apimodel.Components.Companion.so2ID

class PollutantTest : TestCase() {

    public override fun setUp() {
        super.setUp()
    }

    public override fun tearDown() {}

    private val level = 23f

    fun testCreatePollutantCO() {
        val co = Pollutant.createPollutant(coID, level)
        assertTrue(co is CO)
        assertEquals(co.level, level)
    }

    fun testCreatePollutantNO2() {
        val no2 = Pollutant.createPollutant(no2ID, level)
        assertTrue(no2 is NO2)
        assertEquals(no2.level, level)
    }

    fun testCreatePollutantO3() {
        val o3 = Pollutant.createPollutant(o3ID, level)
        assertTrue(o3 is O3)
        assertEquals(o3.level, level)
    }

    fun testCreatePollutantPM2Point5() {
        val pm2Point5 = Pollutant.createPollutant(pm2_5ID, level)
        assertTrue(pm2Point5 is PM2Point5)
        assertEquals(pm2Point5.level, level)
    }

    fun testCreatePollutantPM10() {
        val pm10 = Pollutant.createPollutant(pm10ID, level)
        assertTrue(pm10 is PM10)
        assertEquals(pm10.level, level)
    }

    fun testCreatePollutantSO2() {
        val so2 = Pollutant.createPollutant(so2ID, level)
        assertTrue(so2 is SO2)
        assertEquals(so2.level, level)
    }

}