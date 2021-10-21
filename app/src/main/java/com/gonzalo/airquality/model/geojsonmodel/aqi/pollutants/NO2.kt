package com.gonzalo.airquality.model.geojsonmodel.aqi.pollutants

class NO2(level: Float) : Pollutant(level) {

    override val rangeGood = 0f..54f
    override val rangeModerate = 54f..100f
    override val rangeUnhealthySensitive = 100f..360f
    override val rangeUnHealthy = 36f..649f
    override val rangeVeryUnHealthy = 649f..1249f
    override val rangeHazardousLow = 1249f..1649f
    override val rangeHazardousHigh = 1649f..2050f

}
