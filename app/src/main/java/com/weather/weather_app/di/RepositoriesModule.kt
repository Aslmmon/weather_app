package com.weather.weather_app.di

import com.weather.weather_app.features.forecast.framework.WeatherDataRepoImplementation
import com.weather.weather_app.features.main.framework.MainHomeRepoImplementation
import org.koin.dsl.module

val repositoriesModule = module {
    factory { WeatherDataRepoImplementation(get(),get()) }
    factory { MainHomeRepoImplementation(get()) }

}