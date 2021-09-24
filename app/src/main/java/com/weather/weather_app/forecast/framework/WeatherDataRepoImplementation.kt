package com.weather.weather_app.forecast.framework

import com.floriaapp.core.api.weatherApi
import com.floriaapp.core.entity.WeatherNeededData
import com.floriaapp.core.repo.WeatherDataRepo

class WeatherDataRepoImplementation(var api :weatherApi) : WeatherDataRepo {
    override suspend fun requestAllWeatherData(): WeatherNeededData  = api.getWeatherData().list

    override suspend fun saveAllWeatherData(weatherData: WeatherNeededData) {
        TODO("Not yet implemented")
    }
}