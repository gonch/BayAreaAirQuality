package com.gonzalo.airquality.model.geojsonmodel.aqi.pollutants

class O3(level: Float) : Pollutant(level) {

    override val rangeGood = 0f..54f
    override val rangeModerate = 55f..70f
    override val rangeUnhealthySensitive = 71f..85f
    override val rangeUnHealthy = 86f..105f
    override val rangeVeryUnHealthy = 106f..200f
    override val rangeHazardousLow = rangeInvalid
    override val rangeHazardousHigh = rangeInvalid

}