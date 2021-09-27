package com.weather.weather_app.features.main.presentation

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import com.floriaapp.core.entity.CitiesEntities
import com.floriaapp.core.use_cases.forecast.MAXIMUM_CITIES
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.Task
import com.test.utils.*
import com.test.utils.Bases.BaseActivity
import com.test.utils.PermissionUtils.hasPermission
import com.weather.weather_app.R
import com.weather.weather_app.common.Ext.enableGps
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

        /**
         * initialization of adapter and setting adapter of REcyclerView to it
         */
        initAdapterAndRecyclerView()

        /**
         * getting Permission for First Time Only
         */
        if (!isPerrmissionCheckd()) getPermission()


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
        mainActivityViewModel.getSavedLists()
    }

    private fun getPermission() {
        sharedPrefrenceEditor.putBoolean(PermissionChecked, true).apply()
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

        /**
         * Checking of GPS is Enable , So Get Location
         *
         * if Not checked User is allowed to open GPS to get lastKnownLocation
         *
         *
         */
        if (isGpsProviderOpen()) getCurrentLocation()
        else Navigation.gotoGpsActivity(this, GPSCHECKED)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == GPSCHECKED) {
            if (isGpsProviderOpen() and isPerrmissionCheckd()) {
                getCurrentLocation()
            }

        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    @SuppressLint("MissingPermission")
    private fun getCurrentLocation() {

        /**
         *
         * Getting Location Login , dupend on FusedLocationCurrentLocation
         *
         */
        if (applicationContext.hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {

            val currentLocationTask: Task<Location> = fusedLocationClient.getCurrentLocation(
                LocationRequest.PRIORITY_HIGH_ACCURACY,
                cancellationTokenSource.token
            )
            currentLocationTask.addOnCompleteListener { task: Task<Location> ->

                /**
                 *
                 * Location Accessed then by Geocoder , getting Name Of Country and it's Code
                 *
                 * to be submittied in the list of MainListCities as Default and to be passed to APi of OpenWeatherAPi
                 *
                 *
                 */
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
                submitList(it.take(5).toMutableList())
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
                grantResults[0] == PackageManager.PERMISSION_GRANTED -> requestCurrentLocation()
                else -> addDefultCountry(DEFAULT_COUNTRY, DEFAULT_COUNTRY_CODE)
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

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        cancellationTokenSource.cancel()

    }

    private fun maximumAllowedCities() = sharedPrefrence.getInt(CitiesCounter, 0) <= com.test.utils.MAXIMUM_CITIES

    private fun isPerrmissionCheckd() = sharedPrefrence.getBoolean(PermissionChecked, false)

}