package com.weather.weather_app.features.splash.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.floriaapp.core.entity.CitiesEntities
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.test.utils.CITIES_DATA
import com.weather.weather_app.common.Ext.getJsonFromAssets
import com.weather.weather_app.R
import com.weather.weather_app.common.Ext.saveList
import com.weather.weather_app.features.main.presentation.MainActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.reflect.Type

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val jsonNeeded = getJsonFromAssets(this, "city.json")
        val gson = Gson()
        val listUserType: Type = object : TypeToken<List<CitiesEntities?>?>() {}.type
        val cities: List<CitiesEntities> = gson.fromJson(jsonNeeded, listUserType)


        when (saveList(CITIES_DATA, cities)) {
            true ->
                GlobalScope.launch {
                    delay(2000)
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()

                }
        }


    }
}