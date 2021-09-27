package com.weather.weather_app.features.main.presentation

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import com.floriaapp.core.entity.CitiesEntities
import com.floriaapp.core.use_cases.forecast.MAXIMUM_CITIES
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.Task
import com.test.utils.Bases.BaseActivity
import com.test.utils.CitiesCounter
import com.test.utils.DEFAULT_COUNTRY
import com.test.utils.LOCATION_PERMISSON
import com.test.utils.PermissionUtils.hasPermission
import com.weather.weather_app.R
import com.weather.weather_app.common.Ext.getCountryName
import com.weather.weather_app.common.Ext.isGpsProviderOpen
import com.weather.weather_app.common.Ext.showToast
import com.weather.weather_app.common.Navigation
import com.weather.weather_app.databinding.ActivityMainBinding
import com.weather.weather_app.features.main.presentation.adapter.CitiesMainListAdapter
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel


@RequiresApi(Build.VERSION_CODES.M)
class MainActivity : BaseActivity(), CitiesMainListAdapter.OnItemClickOfProduct {
    lateinit var mainActivityBinding: ActivityMainBinding
    private val mainActivityViewModel: MainActivityViewModel by viewModel()
    private var allCountriesList = mutableListOf<CitiesEntities>()
    private var mainHomeCountriesList = mutableListOf<CitiesEntities>()

    private val sharedPrefrenceEditor: SharedPreferences.Editor by inject()
    private val sharedPrefrence: SharedPreferences by inject()

    private val fusedLocationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(applicationContext)
    }
    private var cancellationTokenSource = CancellationTokenSource()


    lateinit var adapter: CitiesMainListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityBinding.root)
        initAdapterAndRecyclerView()
        getPermission()


        with(mainActivityBinding) {
            btnAdd.setOnClickListener {
                showCitiesDialog(citiis = allCountriesList,
                    functionNeeded = { city ->
                        addCityToMainList(city)
                    })
            }
        }

        mainActivityViewModel.Error.observe(this, Observer {
            showToast(it)
        })
        mainActivityViewModel.getCitiesNeeded()

        observers()


    }


    override fun onResume() {
        super.onResume()
        if (isGpsProviderOpen()) getCurrentLocation()
        mainActivityViewModel.getSavedLists()
    }

    private fun getPermission() {
        val permissionApproved =
            applicationContext.hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)
        if (permissionApproved) {
            requestCurrentLocation()
        } else {
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSON
            )
        }
    }

    private fun requestCurrentLocation() {
        if (isGpsProviderOpen()) getCurrentLocation()
        else Navigation.gotoGpsActivity(this)

    }


    @SuppressLint("MissingPermission")
    private fun getCurrentLocation() {
        if (applicationContext.hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {

            val currentLocationTask: Task<Location> = fusedLocationClient.getCurrentLocation(
                LocationRequest.PRIORITY_HIGH_ACCURACY,
                cancellationTokenSource.token
            )

            currentLocationTask.addOnCompleteListener { task: Task<Location> ->
                val result = if (task.isSuccessful && task.result != null) {
                    val result: Location = task.result
                    getCountryName(
                        result.latitude,
                        result.longitude
                    )?.let { addDefultCountry(it.substringBefore(","), it.substringAfter(",")) }

                    "Location (success): ${result.latitude}, ${result.longitude}"
                } else {
                    val exception = task.exception
                    //  showToast(resources.getString(R.string.checkOpenLocation))
                    "Location (failure): $exception"
                }
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_items, menu);
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_search -> showCitiesDialog(
                citiis = allCountriesList,
                isFromSearch = true,
                searchFunctionality = { city ->
                    addCityToMainList(city)
                    checkIfCityPresentedToNavigateWithSameCity(city)
                })
        }
        return super.onOptionsItemSelected(item)
    }

    private fun checkIfCityPresentedToNavigateWithSameCity(city: CitiesEntities?) {

        val isReturned = getCityFoundinList(city)
        if (isReturned != null) {
            /**
             * navigate with alredy presented  Object of City that has data in it
             */
            Navigation.goToForecastActivity(isReturned, this)
        } else {
            /**
             * navigate with new Object of City with no data
             */
            Navigation.goToForecastActivity(city, this)
        }


    }

    private fun observers() {
        mainActivityViewModel.citiesMainList.observe(this, Observer {
            mainHomeCountriesList = it
            sharedPrefrenceEditor.putInt(CitiesCounter, it.size).apply()
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
            allCountriesList = citiesData
        })
        mainActivityViewModel.cityInteraction.observe(this, Observer { isinteract ->
            if (isinteract) mainActivityViewModel.getSavedLists()
        })
    }

    private fun initAdapterAndRecyclerView() {
        adapter = CitiesMainListAdapter(this)
        mainActivityBinding.rvCities.adapter = adapter

    }

    override fun onItemClicked(position: Int, item: CitiesEntities) {
        Navigation.goToForecastActivity(item, this)
    }

    override fun onDeleteItemClicked(position: Int, item: CitiesEntities) {
        mainActivityViewModel.removeCity(item)
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {

        if (requestCode == LOCATION_PERMISSON) {
            when {
                grantResults.isEmpty() -> {
                }
                grantResults[0] == PackageManager.PERMISSION_GRANTED -> requestCurrentLocation()
                else -> addDefultCountry(DEFAULT_COUNTRY, "UK")

            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }

    private fun addDefultCountry(countryName: String, countryCode: String) {
        mainActivityViewModel.addCity(CitiesEntities(0, countryName, country = countryCode))
    }


    private fun addCityToMainList(city: CitiesEntities?) {
        if (!isCityFoundinList(city) and maximumAllowedCities()) city?.apply {
            mainActivityViewModel.addCity(
                this
            )
        }
    }

    private fun isCityFoundinList(city: CitiesEntities?): Boolean {
        return mainHomeCountriesList.firstOrNull { it.id == city?.id } != null
    }

    private fun isCityFoundinListReturnSameObject(city: CitiesEntities?): CitiesEntities? {
        return mainHomeCountriesList.firstOrNull() { it.id == city?.id }
    }

    private fun getCityFoundinList(city: CitiesEntities?): CitiesEntities? {
        return if (isCityFoundinListReturnSameObject(city) != null) isCityFoundinListReturnSameObject(
            city
        ) else null
    }

    private fun maximumAllowedCities() = sharedPrefrence.getInt(CitiesCounter, 0) <= MAXIMUM_CITIES

}