package com.gonzalo.airquality

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gonzalo.airquality.model.geojsonmodel.GeoJsonAQI
import com.gonzalo.airquality.model.geojsonmodel.GeoJsonAQIBuilder
import com.gonzalo.airquality.repositories.AirQualityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class AirQualityViewModel @Inject constructor(
    private var geoJsonAQIBuilder: GeoJsonAQIBuilder,
    private var airQualityRepository: AirQualityRepository)
    : ViewModel() {

    private val _airQualitiesLiveData = MutableLiveData<List<GeoJsonAQI>>()
    val airQualitiesLiveData : LiveData<List<GeoJsonAQI>> = _airQualitiesLiveData

    private val _error = MutableLiveData<String>()
    val error : LiveData<String> = _error

    private val initialTime = LocalDateTime.now()
    private val _currentTime = MutableLiveData(initialTime)
    val currentTime : LiveData<LocalDateTime> = _currentTime

    fun getAirQualityData() {
         viewModelScope.launch(Dispatchers.IO) {
                val airQualities = airQualityRepository.fetchAirQualities()
                if (airQualities.error == null) {
                    _airQualitiesLiveData.postValue(geoJsonAQIBuilder.build(airQualities.asList()))
                } else {
                    _error.postValue(airQualities.error!!.errorMessage)
                }
         }
    }

    fun setCurrentHourDelta(hours: Int) {
        _currentTime.value = initialTime.plusHours(hours.toLong())
    }

    fun isDayTime() : Boolean {
        return _currentTime.value?.hour in 7..19
    }
}