package com.gonzalo.airquality.model.geojsonmodel

data class Geometry(
    val coordinates: List<Double>,
    val type: String
)