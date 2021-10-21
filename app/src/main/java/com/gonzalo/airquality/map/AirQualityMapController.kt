package com.gonzalo.airquality.map

import android.graphics.Color
import android.os.Bundle
import com.gonzalo.airquality.model.geojsonmodel.GeoJsonAQI
import com.google.gson.Gson
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.geometry.LatLngBounds
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.style.expressions.Expression.*
import com.mapbox.mapboxsdk.style.layers.CircleLayer
import com.mapbox.mapboxsdk.style.layers.PropertyFactory.*
import com.mapbox.mapboxsdk.style.layers.SymbolLayer
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource


class AirQualityMapController (private val map : MapView,
                               private var isDaylight: Boolean) {

    fun interface AirQualityMapControllerReadyCallback {
        fun onMapIsReady()
    }

    // Colors
    private val COLOR_ALPHA = 0.75
    private val COLOR_GREEN = rgba(51, 195, 119, COLOR_ALPHA)
    private val COLOR_YELLOW = rgba(217, 216, 56, COLOR_ALPHA)
    private val COLOR_ORANGE = rgba(247, 150, 64, COLOR_ALPHA)
    private val COLOR_RED = rgba(247, 78, 78, COLOR_ALPHA)
    private val COLOR_PURPLE = rgba(119, 83, 235, COLOR_ALPHA)
    private val COLOR_MAROON = rgba(128, 0, 0, COLOR_ALPHA)

    // Mapbox IDs
    private val AQI_GEOJSON_SRC_ID = "aqi_source_id"
    private val AQI_LABELS_LAYER_ID = "labels_layer_id"
    private val AQI_CIRCLES_LAYER_ID = "circles_layer_id"

    private lateinit var mapboxMap: MapboxMap

    private val DAY_STYLE_URL = "mapbox://styles/gonzaloalsina/ckt645j7h2gb717lpfzpxes4s"
    private val NIGHT_STYLE_URL = "mapbox://styles/gonzaloalsina/cku3pr05p0vpm17pjsfwliydb"

    // Max Bounds
    private val BOUND_CORNER_NW = LatLng(38.2033, -122.6445)
    private val BOUND_CORNER_SE = LatLng(37.1897, -121.5871)
    private val RESTRICTED_BOUNDS_AREA = LatLngBounds.Builder()
        .include(BOUND_CORNER_NW)
        .include(BOUND_CORNER_SE)
        .build()


    private var geoJsonAQIs : List<GeoJsonAQI> = listOf()


    fun onCreate(savedInstanceState : Bundle?, readyCallback: AirQualityMapControllerReadyCallback) {
        map.onCreate(savedInstanceState)
        map.getMapAsync { mapboxMap ->
            mapboxMap.setStyle(
                getMapStyle()
            ) {
                this.mapboxMap = mapboxMap
                setUpMapUI(mapboxMap)
                readyCallback.onMapIsReady()
            }
        }
    }

    fun redrawUI(hour: Int) {
        if (geoJsonAQIs.isNotEmpty()) {
            this.mapboxMap.setStyle(getMapStyle()) {
                clearMapStyle(it)
                updateMapStyle(it, hour)
            }
        }
    }

    private fun updateMapStyle(style: Style, hour : Int) {
        style.addSource(GeoJsonSource(AQI_GEOJSON_SRC_ID, Gson().toJson(geoJsonAQIs[hour/2])))
        addAQICircles(style)
    }

    private fun clearMapStyle(style: Style) {
        style.removeLayer(AQI_LABELS_LAYER_ID)
        style.removeLayer(AQI_CIRCLES_LAYER_ID)
        style.removeSource(AQI_GEOJSON_SRC_ID)
    }

    fun updateAQIs(newGeoJsonAQIs: List<GeoJsonAQI>) {
        this.geoJsonAQIs = newGeoJsonAQIs
    }

    private fun addAQICircles(style: Style) {
        val aqiLabels = SymbolLayer(AQI_LABELS_LAYER_ID, AQI_GEOJSON_SRC_ID)
            .withProperties(
                textField(get("aqi")),
                textSize(14f),
                textColor(Color.WHITE),
                textOpacity(0.9f),
                textIgnorePlacement(true),
                textAllowOverlap(true)
            )
        val aqiCircles = CircleLayer(AQI_CIRCLES_LAYER_ID, AQI_GEOJSON_SRC_ID)
            .withProperties(
                circleRadius(70f),
                circleBlur(0.95f),
                circleColor(
                    step(get("aqi"), rgb(0, 0, 0),
                    stop(0, COLOR_GREEN),
                    stop(51, COLOR_YELLOW),
                    stop(101, COLOR_ORANGE),
                    stop(151, COLOR_RED),
                    stop(201, COLOR_PURPLE),
                    stop(301, COLOR_MAROON))
                )
            )
        style.removeLayer(AQI_LABELS_LAYER_ID)
        style.removeLayer(AQI_CIRCLES_LAYER_ID)
        style.addLayer(aqiCircles)
        style.addLayer(aqiLabels)
    }

    fun setDayTime(daylight: Boolean) {
        isDaylight = daylight
    }

    private fun setUpMapUI(mapboxMap: MapboxMap) {
        mapboxMap.setLatLngBoundsForCameraTarget(RESTRICTED_BOUNDS_AREA)
        mapboxMap.setMinZoomPreference(7.75)
        mapboxMap.setMaxZoomPreference(9.00)
        mapboxMap.uiSettings.isCompassEnabled = false
        mapboxMap.uiSettings.isRotateGesturesEnabled = false
        mapboxMap.uiSettings.isTiltGesturesEnabled = false
    }

    private fun getMapStyle(): Style.Builder {
        return if (isDaylight) {
            Style.Builder().fromUri(DAY_STYLE_URL)
        } else {
            Style.Builder().fromUri(NIGHT_STYLE_URL)
        }
    }

    fun onStart() {
        map.onStart()
    }

    fun onResume() {
        map.onResume()
    }

    fun onPause() {
        map.onPause()
    }

    fun onStop() {
        map.onStop()
    }

    fun onSaveInstanceState(outState: Bundle) {
        map.onSaveInstanceState(outState)
    }

    fun onLowMemory() {
        map.onLowMemory()
    }

    fun onDestroy() {
        map.onDestroy()
    }
}