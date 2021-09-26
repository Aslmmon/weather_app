package com.weather.weather_app.features.main.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.floriaapp.core.Extensions.launchDataLoad
import com.floriaapp.core.entity.CitiesEntities
import com.floriaapp.core.entity.CitiesNeeded
import com.floriaapp.core.use_cases.main_home.*

class MainActivityViewModel(
    private val getCitiesNeeded: GetCitiesData,
    private val adddCity: AddCity,
    private val RemoveCity: RemoveCity,
    private val allowAddCity: AllowAddCity,
    private val getSavedCities: GetSavedCities,


    ) : ViewModel() {

    val citiesData: MutableLiveData<CitiesNeeded> = MutableLiveData()
    val Error: MutableLiveData<String> = MutableLiveData()

    val citiesMainList: MutableLiveData<CitiesNeeded> = MutableLiveData()
    val isAllowedAddingCity: MutableLiveData<Boolean> = MutableLiveData()
    val cityInteraction: MutableLiveData<Boolean> = MutableLiveData()


    fun getCitiesNeeded() {
        launchDataLoad(execution = {
            citiesData.value = getCitiesNeeded.invoke()
        }, errorReturned = {
            Error.value = it.message.toString()

            Log.e("database get", it.message.toString())
        })
    }

    fun addCity(city: CitiesEntities) {
        launchDataLoad(execution = {
             adddCity(city)
            cityInteraction.value = true
        }, errorReturned = {
            Error.value = it.message.toString()
            Log.e("database add", it.message.toString())
        })
    }

    fun getSavedLists() {
        launchDataLoad(execution = {
            citiesMainList.value = getSavedCities()

        }, errorReturned = {
            Error.value = it.message.toString()
            Log.e("database add", it.message.toString())
        })
    }

    fun removeCity(city: CitiesEntities) {
        launchDataLoad(
            execution = {
                RemoveCity(city)
                cityInteraction.value = true
            },
            errorReturned = {
                Error.value = it.message.toString()
                Log.e("database remove", it.message.toString())
            })
    }

    fun checkForAllowanceOfAddingCities(maximumCitiesAllowed: Int) {
        this.isAllowedAddingCity.value = allowAddCity.invoke(maximumCitiesAllowed)
    }


}