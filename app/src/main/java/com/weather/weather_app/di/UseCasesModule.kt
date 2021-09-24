package com.weather.weather_app.di

import com.floriaapp.core.use_cases.RequestWeatherData
import com.floriaapp.core.use_cases.SaveWeatherData
import com.weather.weather_app.forecast.framework.WeatherDataRepoImplementation
import org.koin.dsl.module

val useCasesModule = module {
    factory { RequestWeatherData(get() as WeatherDataRepoImplementation) }
    factory { SaveWeatherData(get() as WeatherDataRepoImplementation) }

}
