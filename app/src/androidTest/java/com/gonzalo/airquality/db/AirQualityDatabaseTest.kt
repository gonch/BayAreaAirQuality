package com.gonzalo.airquality.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException

class AirQualityDatabaseTest {

    private lateinit var airQualityDatabase: AirQualityDatabase
    private lateinit var airQualityDao: AirQualityDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        airQualityDatabase = Room.inMemoryDatabaseBuilder(
            context, AirQualityDatabase::class.java).build()
        airQualityDao = airQualityDatabase.airQualityDao()
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        airQualityDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun saveAirQualityAndLoadIt() {
        val airQuality = AirQuality(0f, 0f, listOf(1,2,3))
        airQualityDao.save(airQuality)
        val airQualityRetrieved = airQualityDao.load(0f, 0f)
        assertEquals(airQuality, airQualityRetrieved)
    }

    @Test
    @Throws(Exception::class)
    fun loadNonExistentCoordinates() {
        val airQualityRetrieved = airQualityDao.load(0f, 0f)
        assertEquals(airQualityRetrieved, null)
    }

    @Test
    @Throws(Exception::class)
    fun saveAirQualitiesAndLoadThem() {
        val airQuality1 = AirQuality(0f, 0f, listOf(1,2,3))
        val airQuality2 = AirQuality(1f, 1f, listOf(1,2,3))
        val airQuality3 = AirQuality(2f, 2f, listOf(1,2,3))
        airQualityDao.save(airQuality1)
        airQualityDao.save(airQuality2)
        airQualityDao.save(airQuality3)
        var airQualityRetrieved = airQualityDao.load(0f, 0f)
        assertEquals(airQuality1, airQualityRetrieved)
        airQualityRetrieved = airQualityDao.load(1f, 1f)
        assertEquals(airQuality2, airQualityRetrieved)
        airQualityRetrieved = airQualityDao.load(2f, 2f)
        assertEquals(airQuality3, airQualityRetrieved)
    }

    @Test
    @Throws(Exception::class)
    fun saveAirQualityRemoveItAndLoadIt() {
        val airQuality = AirQuality(0f, 0f, listOf(1,2,3))
        airQualityDao.save(airQuality)
        airQualityDao.deleteAll()
        val airQualityRetrieved = airQualityDao.load(0f, 0f)
        assertEquals(airQualityRetrieved, null)
    }

    @Test
    @Throws(Exception::class)
    fun saveAirQualitiesDeleteAll() {
        val airQuality1 = AirQuality(0f, 0f, listOf(1,2,3))
        val airQuality2 = AirQuality(1f, 1f, listOf(1,2,3))
        val airQuality3 = AirQuality(2f, 2f, listOf(1,2,3))
        airQualityDao.save(airQuality1)
        airQualityDao.save(airQuality2)
        airQualityDao.save(airQuality3)
        airQualityDao.deleteAll()
        assertEquals(airQualityDao.getAll(), listOf<Int>())
    }
}