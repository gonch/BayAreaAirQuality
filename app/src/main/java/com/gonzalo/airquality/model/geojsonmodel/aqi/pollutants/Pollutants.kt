package com.gonzalo.airquality.model.geojsonmodel.aqi.pollutants

class Pollutants(val list: List<Pollutant>) : Iterable<Pollutant> {

    override fun iterator(): Iterator<Pollutant> {
        return list.iterator()
    }
}