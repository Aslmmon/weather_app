package com.weather.weather_app.di

import com.floriaapp.core.api.weatherApi
import com.test.utils.Common.network.createNetworkClient
import org.koin.dsl.module
import retrofit2.Retrofit


val retrofit: Retrofit = createNetworkClient()
private val weatherApi: weatherApi = retrofit.create(com.floriaapp.core.api.weatherApi::class.java)


val networkModule = module {
    factory { weatherApi }
}
