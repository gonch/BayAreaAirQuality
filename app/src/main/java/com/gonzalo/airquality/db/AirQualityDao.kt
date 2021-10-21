package com.gonzalo.airquality.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AirQualityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(airQuality : AirQuality)

    @Query("SELECT * FROM airQuality WHERE lon = :queryLon AND lat = :queryLat")
    fun load(queryLon: Float, queryLat: Float) : AirQuality

    @Query("SELECT * FROM airquality")
    fun getAll(): List<AirQuality>

    @Query("DELETE FROM airquality")
    fun deleteAll()
}