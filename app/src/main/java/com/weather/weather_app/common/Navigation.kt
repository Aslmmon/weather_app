package com.weather.weather_app.common

import android.app.Activity
import android.content.Intent
import com.floriaapp.core.entity.CitiesEntities
import com.weather.weather_app.features.forecast.presentation.ForecastActivity

object Navigation {
    fun goToForecastActivity(name: CitiesEntities?, activity:Activity){
        val intent = Intent(activity,ForecastActivity::class.java)
        intent.putExtra("name",name)
        activity.startActivity(intent)
    }
}