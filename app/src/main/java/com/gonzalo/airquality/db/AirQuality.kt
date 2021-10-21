package com.gonzalo.airquality.db

import androidx.room.Entity
import androidx.room.TypeConverters

@Entity(primaryKeys = ["lat", "lon"])
@TypeConverters(Converters::class)
data class AirQuality (
    val lat: Float,
    val lon: Float,
    val aqiForecast : List<Int>
)