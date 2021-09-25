package com.weather.weather_app.common

import android.app.Activity
import android.content.Intent
import com.weather.weather_app.features.forecast.presentation.ForecastActivity

object Navigation {
    fun goToForecastActivity(activity:Activity){
        activity.startActivity(Intent(activity,ForecastActivity::class.java))
    }
}