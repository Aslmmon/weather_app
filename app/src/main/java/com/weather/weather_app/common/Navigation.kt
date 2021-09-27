package com.weather.weather_app.common

import android.app.Activity
import android.content.Intent
import android.provider.Settings
import androidx.activity.result.ActivityResultLauncher
import com.floriaapp.core.entity.CitiesEntities
import com.test.utils.CITY_DATA
import com.weather.weather_app.features.forecast.presentation.ForecastActivity

object Navigation {
    fun goToForecastActivity(name: CitiesEntities?, activity: Activity) {
        val intent = Intent(activity, ForecastActivity::class.java)
        intent.putExtra(CITY_DATA, name)
        activity.startActivity(intent)
    }

    fun gotoGpsActivity(activity: Activity,permissionInt: Int) {
        val callGPSSettingIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        //resultLauncher.launch(callGPSSettingIntent)
        activity.startActivityForResult(callGPSSettingIntent,permissionInt)
    }
}