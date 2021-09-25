package com.weather.weather_app.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.floriaapp.core.entity.CitiesEntities
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.test.utils.Bases.BaseActivity
import com.test.utils.Ext.getJsonFromAssets
import com.test.utils.Ext.showToast
import com.weather.weather_app.databinding.ActivityMainBinding
import com.weather.weather_app.forecast.presentation.ForecastActivity
import com.weather.weather_app.main.adapter.CityListAdapter
import com.weather.weather_app.main.adapter.Test
import java.lang.reflect.Type

class MainActivity : BaseActivity(), CityListAdapter.OnItemClickOfProduct {
    lateinit var mainActivityBinding: ActivityMainBinding
    lateinit var adapter: CityListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityBinding.root)
        initAdapterAndRecyclerView()
        val jsonNeeded = getJsonFromAssets(this, "city.json")
        Log.i("data", jsonNeeded.toString())
        val gson = Gson()
        val listUserType: Type = object : TypeToken<List<CitiesEntities?>?>() {}.type
        val cities: List<CitiesEntities> = gson.fromJson(jsonNeeded, listUserType)

        adapter.submitList(mutableListOf(Test(1, "London"), Test(1, "London"), Test(1, "London")))

        with(mainActivityBinding) {
            btnAdd.setOnClickListener {
                showCitiesDialog(citiis = cities, functionNeeded = { cityName ->
                    showToast(cityName)
                })
            }
        }
    }

    private fun initAdapterAndRecyclerView() {
        adapter = CityListAdapter(this)
        mainActivityBinding.rvCities.adapter = adapter

    }

    override fun onItemClicked(position: Int, item: Test) {
        startActivity(Intent(this, ForecastActivity::class.java))
        //Toast.makeText(this,item.toString(),Toast.LENGTH_LONG).show()
    }


}