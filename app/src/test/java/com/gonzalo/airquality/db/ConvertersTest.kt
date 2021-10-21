package com.gonzalo.airquality.db

import org.junit.Assert.*

class ConvertersTest {

    @org.junit.Test
    fun stringToAqiListAndBack() {
        val aqiList = listOf(1,2,3)
        val aqiListString = Converters().aqiListToString(aqiList)
        assertEquals("[1,2,3]", aqiListString)
        val aqiListConverted = Converters().stringToAqiList(aqiListString)
        assertEquals(aqiList, aqiListConverted)
    }
}