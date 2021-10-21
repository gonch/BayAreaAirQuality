package com.gonzalo.airquality.model.geojsonmodel.aqi.pollutants

class SO2(level: Float) : Pollutant(level) {

    override val rangeGood = 0f..35f
    override val rangeModerate = 35f..75f
    override val rangeUnhealthySensitive = 75f..185f
    override val rangeUnHealthy = 185f..304f
    override val rangeVeryUnHealthy = 304f..604f
    override val rangeHazardousLow = 604f..804f
    override val rangeHazardousHigh = 804f..1004f

}
