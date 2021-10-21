package com.gonzalo.airquality

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.gonzalo.airquality.model.AirQualityBatch
import com.gonzalo.airquality.model.geojsonmodel.GeoJsonAQI
import com.gonzalo.airquality.model.geojsonmodel.GeoJsonAQIBuilder
import com.gonzalo.airquality.repositories.AirQualityRepository
import com.gonzalo.airquality.utils.TestCoroutineRule
import com.gonzalo.airquality.web.ResponseWrapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class AirQualityViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var airQualityRepository : AirQualityRepository

    @Mock
    private lateinit var airQualityDatabserver: Observer<List<GeoJsonAQI>>

    @Mock
    private lateinit var airQualityError: Observer<String>


    @Test
    fun getAirQualityDataSuccess() {
        testCoroutineRule.runBlockingTest {
            val emptyBatch = AirQualityBatch(listOf())

            doReturn(emptyBatch)
                .`when`(airQualityRepository)
                .fetchAirQualities()

            val viewModel = AirQualityViewModel(GeoJsonAQIBuilder(), airQualityRepository)

            viewModel.airQualitiesLiveData.observeForever(airQualityDatabserver)
            viewModel.getAirQualityData()
            verify(airQualityRepository).fetchAirQualities()
            verify(airQualityDatabserver).onChanged(GeoJsonAQIBuilder().build(emptyList()))
            viewModel.airQualitiesLiveData.removeObserver(airQualityDatabserver)
        }
    }

    @Test
    fun getAirQualityErrorDoesntTriggerDataUpdate() {
        testCoroutineRule.runBlockingTest {
            val emptyBatchWithError = AirQualityBatch(listOf())
            emptyBatchWithError.error = ResponseWrapper.Error("error")
            doReturn(emptyBatchWithError)
                .`when`(airQualityRepository)
                .fetchAirQualities()

            val viewModel = AirQualityViewModel(GeoJsonAQIBuilder(), airQualityRepository)
            viewModel.airQualitiesLiveData.observeForever(airQualityDatabserver)
            viewModel.getAirQualityData()
            verify(airQualityRepository).fetchAirQualities()
            verify(airQualityDatabserver, Mockito.times(0)).onChanged(GeoJsonAQIBuilder().build(emptyList()))
            viewModel.airQualitiesLiveData.removeObserver(airQualityDatabserver)
        }
    }

    @Test
    fun getAirQualityErrorTriggersErrorChange() {
        testCoroutineRule.runBlockingTest {
            val errorString = "error"
            val emptyBatchWithError = AirQualityBatch(listOf())
            emptyBatchWithError.error = ResponseWrapper.Error(errorString)
            doReturn(emptyBatchWithError)
                .`when`(airQualityRepository)
                .fetchAirQualities()

            val viewModel = AirQualityViewModel(GeoJsonAQIBuilder(), airQualityRepository)

            viewModel.error.observeForever(airQualityError)

            viewModel.getAirQualityData()
            verify(airQualityRepository).fetchAirQualities()
            verify(airQualityError).onChanged(errorString)

            viewModel.error.removeObserver(airQualityError)
        }
    }
}