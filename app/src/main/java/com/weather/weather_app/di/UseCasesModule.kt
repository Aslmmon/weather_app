package com.weather.weather_app.di

import com.floriaapp.core.use_cases.forecast.RequestWeatherData
import com.floriaapp.core.use_cases.main_home.*
import com.weather.weather_app.features.forecast.framework.WeatherDataRepoImplementation
import com.weather.weather_app.features.main.framework.MainHomeRepoImplementation
import org.koin.dsl.module

val useCasesModule = module {
    factory { RequestWeatherData(get() as WeatherDataRepoImplementation) }
   // factory { SaveWeatherData(get() as WeatherDataRepoImplementation) }
    factory { AddCity(get() as MainHomeRepoImplementation) }
    factory { RemoveCity(get() as MainHomeRepoImplementation) }
    factory { GetCitiesData(get() as MainHomeRepoImplementation) }
    factory { AllowAddCity(get() as MainHomeRepoImplementation) }
    factory { GetSavedCities(get() as MainHomeRepoImplementation) }

}
