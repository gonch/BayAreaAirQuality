package com.gonzalo.airquality.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AirQuality::class], version = 1)
abstract class AirQualityDatabase : RoomDatabase() {
    abstract fun airQualityDao() : AirQualityDao
}