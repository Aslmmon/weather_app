package com.floriaapp.core.api

import com.floriaapp.core.entity.WeatherEntities
import retrofit2.http.GET

interface weatherApi {


    @GET("/data/2.5/forecast?q=London&appid=01ed0bf33f0d52e1bad309739903a79b")
    suspend fun getWeatherData():WeatherEntities


}