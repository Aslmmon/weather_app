package com.weather.weather_app.di

import com.weather.weather_app.forecast.framework.WeatherDataRepoImplementation
import org.koin.dsl.module

val repositoriesModule = module {
    factory { WeatherDataRepoImplementation(get()) }

}