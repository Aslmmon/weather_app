package com.weather.weather_app.features.main.presentation

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.floriaapp.core.entity.CitiesEntities
import com.test.utils.Bases.BaseActivity
import com.weather.weather_app.common.Ext.showToast
import com.weather.weather_app.databinding.ActivityMainBinding
import com.weather.weather_app.features.forecast.presentation.ForecastActivity
import com.weather.weather_app.features.main.presentation.adapter.CityListAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity(), CityListAdapter.OnItemClickOfProduct {
    lateinit var mainActivityBinding: ActivityMainBinding
    private val mainActivityViewModel: MainActivityViewModel by viewModel()
    var citiesMainList = mutableListOf<CitiesEntities>()

    lateinit var adapter: CityListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityBinding.root)
        initAdapterAndRecyclerView()

        mainActivityViewModel.addCity(CitiesEntities(1, "London"), citiesMainList)
        mainActivityViewModel.getCitiesNeeded()

        observers()


    }

    private fun observers() {
        mainActivityViewModel.citiesMainList.observe(this, Observer {
            with(adapter) {
                submitList(it)
                notifyDataSetChanged()
            }
            mainActivityViewModel.checkForAllowanceOfAddingCities(it.size)

        })

        mainActivityViewModel.isAllowedAddingCity.observe(this, Observer { resultAllowance ->
            with(mainActivityBinding) {
                when (resultAllowance) {
                    true -> btnAdd.isClickable = true
                    false -> btnAdd.isClickable = false

                }
            }

        })

        mainActivityViewModel.citiesData.observe(this, Observer { citiesData ->
            with(mainActivityBinding) {
                btnAdd.setOnClickListener {
                    showCitiesDialog(citiis = citiesData,
                        functionNeeded = { city ->
                            mainActivityViewModel.addCity(city, citiesMainList)
                        })
                }
            }
        })
    }

    private fun initAdapterAndRecyclerView() {
        adapter = CityListAdapter(this)
        mainActivityBinding.rvCities.adapter = adapter

    }

    override fun onItemClicked(position: Int, item: CitiesEntities) {
        //  startActivity(Intent(this, ForecastActivity::class.java))
        //Toast.makeText(this,item.toString(),Toast.LENGTH_LONG).show()
    }

    override fun onDeleteItemClicked(position: Int, item: CitiesEntities) {
        mainActivityViewModel.removeCity(item, citiesMainList)
    }


}