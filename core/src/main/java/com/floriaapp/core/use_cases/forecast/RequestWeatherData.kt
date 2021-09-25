package com.floriaapp.core.use_cases.forecast

import com.floriaapp.core.entity.DateWithData
import com.floriaapp.core.entity.ListData
import com.floriaapp.core.repo.WeatherDataRepo

class RequestWeatherData(private val weatherDataRepo: WeatherDataRepo) {
    suspend operator fun invoke(): MutableList<DateWithData> {
        val updatedList = mutableListOf<DateWithData>()
        val list = weatherDataRepo.requestAllWeatherData().groupBy { it.dtTxt.substringBefore(" ") }
            .mapValues { entry ->
                updatedList.add(DateWithData(entry.key, entry.value))
            }
        return updatedList
    }
}

var data = mutableListOf<cord>()

data class cord(var data: HashMap<String, List<ListData>>)