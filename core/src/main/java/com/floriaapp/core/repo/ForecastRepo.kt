package com.floriaapp.core.repo

import com.floriaapp.core.entity.CitiesEntities
import com.floriaapp.core.entity.DateWithData
import com.floriaapp.core.entity.ListData


interface ForecastRepo {
    suspend fun requestAllWeatherData(countryName: String?): List<ListData>
    suspend fun saveAllWeatherData(weatherData: CitiesEntities)
}