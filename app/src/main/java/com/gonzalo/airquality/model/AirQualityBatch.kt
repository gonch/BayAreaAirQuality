package com.gonzalo.airquality.model

import com.gonzalo.airquality.db.AirQuality
import com.gonzalo.airquality.web.ResponseWrapper

class AirQualityBatch (airQualities : List<AirQuality>) : Iterable<AirQuality>{

    private val internalList = airQualities.toMutableList()

    var error : ResponseWrapper.Error? = null

    override fun iterator(): Iterator<AirQuality> {
        return internalList.iterator()
    }

    fun add(airQuality: AirQuality) {
        internalList.add(airQuality)
    }

    fun asList(): List<AirQuality> {
        return internalList.toList()
    }
}