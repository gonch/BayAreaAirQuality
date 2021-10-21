package com.gonzalo.airquality.model.geojsonmodel.aqi.pollutants

class CO(level: Float) : Pollutant(level) {

    override val rangeGood = 0.0f..4.5f
    override val rangeModerate = 4.5f..9.5f
    override val rangeUnhealthySensitive = 9.5f..12.5f
    override val rangeUnHealthy = 12.5f..15.5f
    override val rangeVeryUnHealthy = 15.5f..30.5f
    override val rangeHazardousLow = 30.5f..40.5f
    override val rangeHazardousHigh = 40.5f..50.5f

}
