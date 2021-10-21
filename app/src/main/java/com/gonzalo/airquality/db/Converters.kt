package com.gonzalo.airquality.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters {

    @TypeConverter
    fun stringToAqiList(data: String) : List<Int> {
        val listType = object : TypeToken<List<Int>>() {}.type
        return Gson().fromJson(data, listType)
    }

    @TypeConverter
    fun aqiListToString(someObjects: List<Int>): String {
        return Gson().toJson(someObjects)
    }
}