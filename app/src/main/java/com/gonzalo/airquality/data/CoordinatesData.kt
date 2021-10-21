package com.gonzalo.airquality.data

import com.gonzalo.airquality.model.Coordinates

// SF Neighborhoods
private val HAYES_VALLEY = Coordinates(37.7749f,-122.4194f)
private val CASTRO = Coordinates(37.760889f, -122.435024f)
private val INNER_RICHMOND = Coordinates(37.781012f, -122.465472f)
private val OUTER_RICHOMOND = Coordinates(37.779924f, -122.488641f)
private val OCEAN_BEACH = Coordinates(37.758627f, -122.510865f)
private val INNER_SUNSET = Coordinates(37.760507f, -122.470428f)
private val OUTER_SUNSET = Coordinates(37.753345f, -122.495208f)

// Bay Area Towns
private val SAN_FRANCISCO = Coordinates(37.7749f, -122.4194f)
private val SAN_JOSE = Coordinates(37.336111f, -121.890556f)
private val OAKLAND = Coordinates(37.804444f, -122.270833f)
private val FREMONT = Coordinates(37.548333f, -121.988611f)
private val SANTA_ROSA = Coordinates(38.448611f, -122.704722f)
private val HAYWARD = Coordinates(37.66882f, -122.080796f)
private val SUNNYVALE = Coordinates(37.371111f, -122.0375f)
private val CONCORD = Coordinates(37.978056f, -122.031111f)
private val VALLEJO = Coordinates(38.113056f, -122.235833f)
private val BERKELEY = Coordinates(37.871667f, -122.272778f)
private val FAIRFIELD = Coordinates(38.257778f, -122.054167f)
private val SAUSALITO = Coordinates(37.859167f, -122.485278f)
private val SEBASTOPOL = Coordinates(38.399167f, -122.826944f)
private val SAN_RAFAEL = Coordinates(37.973611f, -122.531111f)
private val MILLBRAE = Coordinates(37.600833f, -122.401389f)
private val REDWOOD_CITY = Coordinates(37.482778f, -122.236111f)
private val PALO_ALTO = Coordinates(37.429167f, -122.138056f)
private val HALFMOON_BAY = Coordinates(37.458889f, -122.436944f)
private val SANTA_CRUZ = Coordinates(36.974117f, -122.030792f)
private val PETALUMA = Coordinates(38.245833f, -122.631389f)
private val SONOMA = Coordinates(38.288889f, -122.458889f)
private val NAPA = Coordinates(38.304722f, -122.298889f)
private val POINT_REYES = Coordinates(38.030904f, -122.936264f)

object CoordinatesData {
        val SAN_FRANCISCO_NEIGHBORHOODS : List<Coordinates> = listOf(
            HAYES_VALLEY,
            CASTRO,
            INNER_RICHMOND,
            OUTER_RICHOMOND,
            OCEAN_BEACH,
            INNER_SUNSET,
            OUTER_SUNSET)
        
        val BAY_AREA_TOWNS : List<Coordinates> = listOf(
            SAN_FRANCISCO,
            SAN_JOSE,
            OAKLAND,
            FREMONT,
            SANTA_ROSA,
            HAYWARD,
            SUNNYVALE,
            CONCORD,
            VALLEJO,
            BERKELEY,
            FAIRFIELD,
            SAUSALITO,
            SEBASTOPOL,
            SAN_RAFAEL,
            MILLBRAE,
            REDWOOD_CITY,
            PALO_ALTO,
            HALFMOON_BAY,
            SANTA_CRUZ,
            PETALUMA,
            SONOMA,
            NAPA,
            POINT_REYES
        )
}
