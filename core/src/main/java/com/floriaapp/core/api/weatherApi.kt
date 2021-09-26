package com.floriaapp.core.api

import com.floriaapp.core.entity.WeatherEntities
import retrofit2.http.GET
import retrofit2.http.Query

interface weatherApi {


    @GET("/data/2.5/forecast")
    suspend fun getWeatherData(
        @Query("q") countryName: String,
        @Query("appid") appid: String = "01ed0bf33f0d52e1bad309739903a79b",
        @Query("units") unit:String="metric"
    ): WeatherEntities


}