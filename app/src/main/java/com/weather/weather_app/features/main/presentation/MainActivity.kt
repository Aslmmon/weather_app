package com.weather.weather_app.features.main.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.floriaapp.core.entity.CitiesEntities
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.test.utils.Bases.BaseActivity
import com.weather.weather_app.R
import com.weather.weather_app.common.Ext.showToast
import com.weather.weather_app.common.Navigation
import com.weather.weather_app.databinding.ActivityMainBinding
import com.weather.weather_app.features.main.presentation.adapter.CityListAdapter
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity(), CityListAdapter.OnItemClickOfProduct {
    lateinit var mainActivityBinding: ActivityMainBinding
    private val mainActivityViewModel: MainActivityViewModel by viewModel()
    var citiesList = mutableListOf<CitiesEntities>()

    private lateinit var fusedLocationClient: FusedLocationProviderClient


    lateinit var adapter: CityListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityBinding.root)
        initAdapterAndRecyclerView()

        with(mainActivityBinding) {
            btnAdd.setOnClickListener {
                showCitiesDialog(citiis = citiesList,
                    functionNeeded = { city ->
                        mainActivityViewModel.addCity(city!!)
                    })
            }
        }
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)






        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1
                );
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1
                );
            }
        }
        mainActivityViewModel.addCity(CitiesEntities(1, "London"))
        mainActivityViewModel.Error.observe(this, Observer {
            showToast(it)
        })
        mainActivityViewModel.getCitiesNeeded()

        observers()


        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                //   showToast(location?.latitude.toString())
//                getCountryName(location?.latitude!!,location.longitude)
                // Got last known location. In some rare situations this can be null.
            }

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_items, menu);
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_search -> showCitiesDialog(
                citiis = citiesList,
                isFromSearch = true,
                searchFunctionality = { city ->
                    showToast(city.toString())
                    Navigation.goToForecastActivity(city?.name!!, city.cityEntityId, this)
                })
        }
        return super.onOptionsItemSelected(item)
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
            citiesList = citiesData
        })
    }

    private fun initAdapterAndRecyclerView() {
        adapter = CityListAdapter(this)
        mainActivityBinding.rvCities.adapter = adapter

    }

    override fun onItemClicked(position: Int, item: CitiesEntities) {
        Navigation.goToForecastActivity(item.name, item.cityEntityId, this)
        //Toast.makeText(this,item.toString(),Toast.LENGTH_LONG).show()
    }

    override fun onDeleteItemClicked(position: Int, item: CitiesEntities) {
        mainActivityViewModel.removeCity(item)
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(
                            this@MainActivity,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}