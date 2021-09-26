package com.weather.weather_app.common

import android.app.Activity
import android.content.Intent
import com.weather.weather_app.features.forecast.presentation.ForecastActivity

object Navigation {
    fun goToForecastActivity(name:String, id: Int, activity:Activity){
        val intent = Intent(activity,ForecastActivity::class.java)
        intent.putExtra("name",name)
        intent.putExtra("id",id)

        activity.startActivity(intent)
    }
}