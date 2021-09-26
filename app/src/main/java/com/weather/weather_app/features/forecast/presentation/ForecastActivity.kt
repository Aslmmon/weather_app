package com.weather.weather_app.features.forecast.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.floriaapp.core.entity.DateWithData
import com.weather.weather_app.databinding.ActivityForecastBinding
import com.weather.weather_app.features.forecast.presentation.adapter.WeatherForecastsAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class ForecastActivity : AppCompatActivity() {
    private val foreacastViewModel: ForecastViewModel by viewModel()
    lateinit var adapter:WeatherForecastsAdapter
    lateinit var binding: ActivityForecastBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForecastBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initADapter()
        val data =intent.extras
        val countryName = data?.getString("name")
        val countryID = data?.getInt("id")

        foreacastViewModel.reuqestWeatherData(countryName!!,countryID!!)
        foreacastViewModel.weatherData.observe(this, Observer {
            Log.i("data",it.toString())
            bindDatatoViews(it)
        })
    }

    private fun initADapter() {
        adapter = WeatherForecastsAdapter()
        binding.rvTemps.adapter = adapter
    }

    private fun bindDatatoViews(it: MutableList<DateWithData>) {
        adapter.submitList(it)
    }
}