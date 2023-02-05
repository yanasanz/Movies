package com.yanasanz.movies.dao

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yanasanz.movies.dto.Country
import com.yanasanz.movies.dto.Genre

class Converters {

    @TypeConverter
    fun fromListGenre(list: List<Genre?>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toListGenre(string: String?): List<Genre> {
        val type = object : TypeToken<List<Genre>>() {}.type
        return Gson().fromJson(string, type)
    }

    @TypeConverter
    fun fromListCountry(list: List<Country?>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toListCountry(string: String?): List<Country> {
        val type = object : TypeToken<List<Country>>() {}.type
        return Gson().fromJson(string, type)
    }

}