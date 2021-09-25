package com.weather.weather_app.di

import com.weather.weather_app.features.forecast.presentation.ForecastViewModel
import com.weather.weather_app.features.main.presentation.MainActivityViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { ForecastViewModel(get(), get()) }
    viewModel { MainActivityViewModel(get(), get(),get(),get()) }

}