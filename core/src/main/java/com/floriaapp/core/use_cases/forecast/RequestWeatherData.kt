package com.floriaapp.core.use_cases.forecast

import android.util.Log
import com.floriaapp.core.entity.CitiesEntities
import com.floriaapp.core.entity.DateWithData
import com.floriaapp.core.entity.ListData
import com.floriaapp.core.repo.ForecastRepo

class RequestWeatherData(private val weatherDataRepo: ForecastRepo) {
    suspend operator fun invoke(countryName: String, countryID: Int): MutableList<DateWithData> {
        val updatedList = mutableListOf<DateWithData>()
        weatherDataRepo.requestAllWeatherData(countryName).groupBy { it.dtTxt.substringBefore(" ") }
            .mapValues { entry ->
                Log.i("shit", entry.toString())
                updatedList.add(DateWithData(date = entry.key, listNeeded = entry.value))
            }
        weatherDataRepo.saveAllWeatherData(CitiesEntities(cityEntityId = countryID,name =countryName ,WeatherData = updatedList))
        return updatedList
    }
}

var data = mutableListOf<cord>()

data class cord(var data: HashMap<String, List<ListData>>)