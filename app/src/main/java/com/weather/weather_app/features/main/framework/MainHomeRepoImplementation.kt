package com.weather.weather_app.features.main.framework

import com.floriaapp.core.db.dao.MainCitiesListDAO
import com.floriaapp.core.entity.CitiesEntities
import com.floriaapp.core.entity.CitiesNeeded
import com.floriaapp.core.repo.MainHomeRepo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.test.utils.CITY_JSON_FILENAME
import com.test.utils.MAXIMUM_CITIES
import com.weather.weather_app.App
import com.weather.weather_app.common.Ext.getJsonFromAssets
import java.lang.reflect.Type

class MainHomeRepoImplementation(
    private val mainCitiesListDAO: MainCitiesListDAO) : MainHomeRepo {
    override suspend fun getCitiesData(): CitiesNeeded {
        val jsonNeeded =
            App.getAppContext().getJsonFromAssets(App.getAppContext(), CITY_JSON_FILENAME)
        val gson = Gson()
        val listUserType: Type = object : TypeToken<List<CitiesEntities?>?>() {}.type
        val cities: CitiesNeeded = gson.fromJson(jsonNeeded, listUserType)
        return cities
    }


    override suspend fun addCity(
        city: CitiesEntities) {
        mainCitiesListDAO.insertAll( *arrayOf(city))


    }

    override suspend fun removeCity(
        city: CitiesEntities) {
        mainCitiesListDAO.delete(city)
    }

    override suspend fun getSavedCities(): CitiesNeeded {
        return mainCitiesListDAO.getAll()
    }

    override fun isAllowedAddingCity(numberOfAllowedAddition: Int): Boolean {
        return when (numberOfAllowedAddition) {
            in 0..MAXIMUM_CITIES -> true
            else -> false
        }
    }
}