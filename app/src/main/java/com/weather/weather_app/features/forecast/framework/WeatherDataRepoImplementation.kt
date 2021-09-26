package com.weather.weather_app.features.forecast.framework

import com.floriaapp.core.api.weatherApi
import com.floriaapp.core.db.dao.ForecastDAO
import com.floriaapp.core.db.dao.MainCitiesListDAO
import com.floriaapp.core.entity.CitiesEntities
import com.floriaapp.core.entity.ListData
import com.floriaapp.core.repo.ForecastRepo

class WeatherDataRepoImplementation(
    var api: weatherApi,
    var forecastDAO: ForecastDAO,
    var mainCitiesListDAO: MainCitiesListDAO
) : ForecastRepo {

    override suspend fun requestAllWeatherData(countryName: String?): List<ListData> {
        val list = api.getWeatherData(countryName!!)
        return list.list
    }


    override suspend fun saveAllWeatherData(weatherData: CitiesEntities) =
        forecastDAO.insertAll(weatherData)
}