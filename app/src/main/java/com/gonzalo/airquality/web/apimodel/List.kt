package com.gonzalo.airquality.web.apimodel

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class List {
    @SerializedName("main")
    @Expose
    var main: Main? = null

    @SerializedName("components")
    @Expose
    var components: Components = Components()

    @SerializedName("dt")
    @Expose
    var dt: Int = 0
}