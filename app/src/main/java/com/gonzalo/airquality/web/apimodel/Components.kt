package com.gonzalo.airquality.web.apimodel

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Components {

    companion object {
        const val coID = "co"
        const val noID = "no"
        const val no2ID = "no2"
        const val o3ID = "o3"
        const val so2ID = "so2"
        const val pm2_5ID = "pm2_5"
        const val pm10ID = "pm10"
        const val nh3ID = "nh3"
    }

    @SerializedName(coID)
    @Expose
    var co: Double = 0.0

    @SerializedName(noID)
    @Expose
    var no: Double = 0.0

    @SerializedName(no2ID)
    @Expose
    var no2: Double = 0.0

    @SerializedName(o3ID)
    @Expose
    var o3: Double = 0.0

    @SerializedName(so2ID)
    @Expose
    var so2: Double = 0.0

    @SerializedName(pm2_5ID)
    @Expose
    var pm25: Double = 0.0

    @SerializedName(pm10ID)
    @Expose
    var pm10: Double = 0.0

    @SerializedName(nh3ID)
    @Expose
    var nh3: Double = 0.0
}