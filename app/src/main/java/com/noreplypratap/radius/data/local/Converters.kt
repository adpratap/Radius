package com.noreplypratap.radius.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.noreplypratap.radius.model.Exclusion
import com.noreplypratap.radius.model.Facility
import com.noreplypratap.radius.model.Option

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun fromOption(option: List<Option>): String{
        return gson.toJson(option)
    }

    @TypeConverter
    fun toOption(data: String): List<Option>{
        val listType = object : TypeToken<List<Option>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun fromExclusions(exclusion: List<List<Exclusion>>): String{
        return gson.toJson(exclusion)
    }

    @TypeConverter
    fun toExclusions(data: String): List<List<Exclusion>>{
        val listType = object : TypeToken<List<List<Exclusion>>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun fromFacilities(facility: List<Facility>): String{
        return gson.toJson(facility)
    }

    @TypeConverter
    fun toFacilities(data: String): List<Facility>{
        val listType = object : TypeToken<List<Facility>>() {}.type
        return gson.fromJson(data, listType)
    }

}