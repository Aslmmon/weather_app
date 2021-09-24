package com.weather.weather_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.weather.weather_app.adapter.CityListAdapter
import com.weather.weather_app.adapter.Test
import com.weather.weather_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), CityListAdapter.OnItemClickOfProduct {
    lateinit var mainActivityBinding: ActivityMainBinding
    lateinit var adapter: CityListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityBinding.root)
        initAdapterAndRecyclerView()

        adapter.submitList(mutableListOf(Test(1, "London"),Test(1, "London"),Test(1, "London")))

    }

    private fun initAdapterAndRecyclerView() {
        adapter = CityListAdapter(this)
        mainActivityBinding.rvCities.adapter = adapter

    }

    override fun onItemClicked(position: Int, item: Test) {
        Toast.makeText(this,item.toString(),Toast.LENGTH_LONG).show()
    }


}