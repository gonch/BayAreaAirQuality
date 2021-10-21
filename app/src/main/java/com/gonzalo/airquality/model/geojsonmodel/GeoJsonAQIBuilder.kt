package com.gonzalo.airquality.model.geojsonmodel

import com.gonzalo.airquality.db.AirQuality
import javax.inject.Inject

class GeoJsonAQIBuilder @Inject constructor() {

    fun build(listOfAirQualities: List<AirQuality>) : List<GeoJsonAQI> {
        val type = "FeatureCollection"
        val listGeoJsonAQI : MutableList<GeoJsonAQI> = mutableListOf()
        for (hour in 0..24 step 2) {
            val listFeatures : MutableList<Feature> = mutableListOf()
            for (airQuality in listOfAirQualities) {
                val geometry = Geometry(listOf(airQuality.lon.toDouble(), airQuality.lat.toDouble()), "Point")
                val properties = Properties(airQuality.aqiForecast[hour])
                val featureType = "Feature"
                listFeatures.add(Feature(geometry, properties, featureType))
            }
            val geoJson = GeoJsonAQI(listFeatures,type)
            listGeoJsonAQI.add(geoJson)
        }

        return listGeoJsonAQI
    }
}