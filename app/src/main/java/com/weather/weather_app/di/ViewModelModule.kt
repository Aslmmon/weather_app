package com.weather.weather_app.di

import com.weather.weather_app.forecast.presentation.ForecastViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { ForecastViewModel(get(), get()) }
}