package com.gonzalo.airquality.model.geojsonmodel.aqi.pollutants

class PM10(level: Float) : Pollutant(level) {

    override val rangeGood = 0f..54f
    override val rangeModerate = 54f..154f
    override val rangeUnhealthySensitive = 154f..254f
    override val rangeUnHealthy = 254f..354f
    override val rangeVeryUnHealthy = 354f..424f
    override val rangeHazardousLow = 424f..504f
    override val rangeHazardousHigh = 505f..604f

}
