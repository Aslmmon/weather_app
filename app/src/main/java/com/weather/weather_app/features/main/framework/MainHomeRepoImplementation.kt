package com.weather.weather_app.features.main.framework

import com.floriaapp.core.entity.CitiesEntities
import com.floriaapp.core.entity.CitiesNeeded
import com.floriaapp.core.repo.MainHomeRepo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.weather.weather_app.App
import com.weather.weather_app.common.Ext.getJsonFromAssets
import java.lang.reflect.Type

class MainHomeRepoImplementation() : MainHomeRepo {
    override fun getCitiesData(): CitiesNeeded {
        val jsonNeeded = App.getAppContext().getJsonFromAssets(App.getAppContext(), "city.json")
        val gson = Gson()
        val listUserType: Type = object : TypeToken<List<CitiesEntities?>?>() {}.type
        val cities: CitiesNeeded = gson.fromJson(jsonNeeded, listUserType)
        return cities
    }


    override fun addCity(
        city: CitiesEntities,
        citiesMainList: MutableList<CitiesEntities>
    ): CitiesNeeded {
        val newList = citiesMainList
        newList.add(city)
        return newList
    }

    override fun removeCity(
        city: CitiesEntities,
        citiesMainList: MutableList<CitiesEntities>
    ): CitiesNeeded {
        val newList = citiesMainList
        newList.remove(city)
        return newList
    }
}