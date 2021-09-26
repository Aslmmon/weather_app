package com.weather.weather_app.features.forecast.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.floriaapp.core.entity.CitiesEntities
import com.floriaapp.core.entity.DateWithData
import com.test.utils.CITY_DATA
import com.weather.weather_app.common.Ext.isInternetAvailable
import com.weather.weather_app.databinding.ActivityForecastBinding
import com.weather.weather_app.features.forecast.presentation.adapter.WeatherForecastsAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class ForecastActivity : AppCompatActivity() {
    private val foreacastViewModel: ForecastViewModel by viewModel()
    private lateinit var adapter: WeatherForecastsAdapter
    private lateinit var binding: ActivityForecastBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForecastBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAdaptersWithRecyclerView()
        val data = intent.extras
        val cityEntity = data?.getParcelable<CitiesEntities>(CITY_DATA)
        when (cityEntity?.WeatherData) {
            null -> {
                fetchDataFromNetwork(cityEntity)
            }
            else -> {
                if (isInternetAvailable()) fetchDataFromNetwork(cityEntity)
                else bindDatatoViews(cityEntity.WeatherData as MutableList<DateWithData>)
            }
        }
        observers()


    }

    private fun fetchDataFromNetwork(cityEntity: CitiesEntities?) {
        foreacastViewModel.reuqestWeatherData(cityEntity?.name ?: "", cityEntity?.id ?: 0)
    }

    private fun observers() {
        foreacastViewModel.weatherData.observe(this, Observer {
            bindDatatoViews(it)
        })
    }

    private fun initAdaptersWithRecyclerView() {
        adapter = WeatherForecastsAdapter()
        binding.rvTemps.adapter = adapter
    }

    private fun bindDatatoViews(it: MutableList<DateWithData>) {
        adapter.submitList(it)
    }
}