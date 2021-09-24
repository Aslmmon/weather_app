package com.weather.weather_app.forecast.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.weather.weather_app.databinding.ActivityForecastBinding
import org.koin.android.viewmodel.ext.android.viewModel

class ForecastActivity : AppCompatActivity() {
    private val foreacastViewModel: ForecastViewModel by viewModel()
    lateinit var binding: ActivityForecastBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForecastBinding.inflate(layoutInflater)
        setContentView(binding.root)
        foreacastViewModel.reuqestWeatherData()
        foreacastViewModel.weatherData.observe(this, Observer {
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        })
    }
}