package com.gonzalo.airquality.model.geojsonmodel.aqi.pollutants

class PM2Point5(level: Float) : Pollutant(level) {

    override val rangeGood = 0f..12.0f
    override val rangeModerate = 12.1f..35.4f
    override val rangeUnhealthySensitive = 35.5f..55.4f
    override val rangeUnHealthy = 55.5f..150.4f
    override val rangeVeryUnHealthy = 150.5f..250.4f
    override val rangeHazardousLow = 250.5f..350.4f
    override val rangeHazardousHigh = 350.5f..500.4f

}