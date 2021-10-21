package com.gonzalo.airquality.web.apimodel

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Main {
    @SerializedName("aqi")
    @Expose
    var aqi: Int? = null
}