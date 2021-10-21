package com.gonzalo.airquality.model.geojsonmodel

data class GeoJsonAQI(
    val features: List<Feature>,
    val type: String
)