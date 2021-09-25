package com.weather.weather_app.features.forecast.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.floriaapp.core.Extensions.launchDataLoad
import com.floriaapp.core.entity.DateWithData
import com.floriaapp.core.entity.ListData
import com.floriaapp.core.entity.WeatherNeededData
import com.floriaapp.core.use_cases.forecast.RequestWeatherData
import com.floriaapp.core.use_cases.forecast.SaveWeatherData

class ForecastViewModel(
    private val requestWeatherData: RequestWeatherData,
    private val saveWeatherData: SaveWeatherData
) : ViewModel() {

    val weatherData: MutableLiveData<MutableList<DateWithData>> = MutableLiveData()


    fun reuqestWeatherData() {
        launchDataLoad(execution = { weatherData.value = requestWeatherData.invoke() },
            errorReturned = {
                Log.e("error", it.message.toString())
        })
    }

}