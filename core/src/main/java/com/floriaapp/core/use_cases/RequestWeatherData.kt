package com.floriaapp.core.use_cases

import com.floriaapp.core.repo.WeatherDataRepo

class RequestWeatherData(private val weatherDataRepo: WeatherDataRepo) {
    suspend operator fun invoke() = weatherDataRepo.requestAllWeatherData()
}