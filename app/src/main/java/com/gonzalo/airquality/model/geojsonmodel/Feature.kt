package com.gonzalo.airquality.model.geojsonmodel

data class Feature(
    val geometry: Geometry,
    val properties: Properties,
    val type: String
)