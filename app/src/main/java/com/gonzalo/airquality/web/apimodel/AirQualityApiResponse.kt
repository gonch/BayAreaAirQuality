package com.gonzalo.airquality.web.apimodel

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import kotlin.collections.List


class AirQualityApiResponse {
    @SerializedName("coord")
    @Expose
    var coord: Coord = Coord()

    @SerializedName("list")
    @Expose
    var list: List<com.gonzalo.airquality.web.apimodel.List> = listOf()
}