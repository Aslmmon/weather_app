package com.weather.weather_app.features.forecast.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.floriaapp.core.Extensions.launchDataLoad
import com.floriaapp.core.Extensions.toErrorBody
import com.floriaapp.core.entity.DateWithData
import com.floriaapp.core.use_cases.forecast.RequestWeatherData

class ForecastViewModel(
    private val requestWeatherData: RequestWeatherData
) : ViewModel() {
    val weatherData: MutableLiveData<MutableList<DateWithData>> = MutableLiveData()
    val Error: MutableLiveData<String> = MutableLiveData()


    fun reuqestWeatherData(countryName: String, countryID: Int,countrCode:String) {
        launchDataLoad(execution = {
            weatherData.value = requestWeatherData.invoke(countryName, countryID,countrCode)
        },
            errorReturned = {
                Error.value = it.toErrorBody()
            })
    }

}