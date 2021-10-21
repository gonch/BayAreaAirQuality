package com.gonzalo.airquality.web.apimodel

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Coord {
    @SerializedName("lon")
    @Expose
    var lon: Float = 0f

    @SerializedName("lat")
    @Expose
    var lat: Float = 0f
}