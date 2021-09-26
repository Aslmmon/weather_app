package com.weather.weather_app.features.main.presentation

import android.Manifest
import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import com.floriaapp.core.entity.CitiesEntities
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.test.utils.Bases.BaseActivity
import com.test.utils.CitiesCounter
import com.test.utils.PermissionUtils.hasPermission
import com.test.utils.PermissionUtils.requestPermissionWithRationale
import com.weather.weather_app.R
import com.weather.weather_app.common.Ext.getCountryName
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
    private val sharedPrefrenceEditor: SharedPreferences.Editor by inject()
    private val fusedLocationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(applicationContext)
    }
    private var cancellationTokenSource = CancellationTokenSource()


    private val fineLocationRationalSnackbar by lazy {
        Snackbar.make(
            mainActivityBinding.btnAdd,
            "R.string.fine_location_permission_rationale",
            Snackbar.LENGTH_LONG
        ).setAction("Ok") {
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
        }
    }


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
                        mainActivityViewModel.addCity(city!!)
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
        val permissionApproved =
            applicationContext.hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)

        if (permissionApproved) {
            requestCurrentLocation()
        } else {
            requestPermissionWithRationale(
                Manifest.permission.ACCESS_FINE_LOCATION,
                1,
                fineLocationRationalSnackbar
            )
        }
    }

    @SuppressLint("MissingPermission")
    private fun requestCurrentLocation() {
        if (applicationContext.hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {

            val currentLocationTask: Task<Location> = fusedLocationClient.getCurrentLocation(
                LocationRequest.PRIORITY_HIGH_ACCURACY,
                cancellationTokenSource.token
            )

            currentLocationTask.addOnCompleteListener { task: Task<Location> ->
                val result = if (task.isSuccessful && task.result != null) {
                    val result: Location = task.result
                    getCountryName(result.latitude, result.longitude)?.let { addDefultCountry(it) }

                    "Location (success): ${result.latitude}, ${result.longitude}"
                } else {
                    val exception = task.exception
                    "Location (failure): $exception"
                }

                showToast(result)
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
                    Navigation.goToForecastActivity(city, this)
                })
        }
        return super.onOptionsItemSelected(item)
    }

    private fun observers() {
        mainActivityViewModel.citiesMainList.observe(this, Observer {
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

        if (requestCode == 1) {
            when {
                grantResults.isEmpty() -> {
                    showToast("empty")
                }
                grantResults[0] == PackageManager.PERMISSION_GRANTED -> showToast("cgranted")

                else -> {
                    addDefultCountry("London")
                    showToast("rejected")
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }

    private fun addDefultCountry(countryName: String) {
        mainActivityViewModel.addCity(CitiesEntities(0, countryName))
    }


    override fun onStop() {
        super.onStop()
        cancellationTokenSource.cancel()
    }
}