package com.floriaapp.core.repo

import com.floriaapp.core.entity.WeatherNeededData


interface WeatherDataRepo {
    suspend fun requestAllWeatherData():WeatherNeededData
    suspend fun saveAllWeatherData(weatherData: WeatherNeededData)
}