package com.floriaapp.core.use_cases.forecast

import com.floriaapp.core.repo.WeatherDataRepo
import com.floriaapp.core.entity.WeatherNeededData

class SaveWeatherData(private val weatherDataRepo: WeatherDataRepo) {
    suspend operator fun invoke(weatherNeededData: WeatherNeededData) =
        weatherDataRepo.saveAllWeatherData(weatherNeededData)

}