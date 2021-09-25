package com.weather.weather_app.features.main.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.floriaapp.core.entity.CitiesEntities
import com.floriaapp.core.entity.CitiesNeeded
import com.floriaapp.core.use_cases.main_home.AddCity
import com.floriaapp.core.use_cases.main_home.AllowAddCity
import com.floriaapp.core.use_cases.main_home.GetCitiesData
import com.floriaapp.core.use_cases.main_home.RemoveCity

class MainActivityViewModel(
    private val getCitiesNeeded: GetCitiesData,
    private val addCity: AddCity,
    private val removeCity: RemoveCity,
    private val allowAddCity: AllowAddCity,

    ) : ViewModel() {

    val citiesData: MutableLiveData<CitiesNeeded> = MutableLiveData()
    val citiesMainList: MutableLiveData<CitiesNeeded> = MutableLiveData()
    val isAllowedAddingCity: MutableLiveData<Boolean> = MutableLiveData()


    fun getCitiesNeeded() {
        citiesData.value = getCitiesNeeded.invoke()
    }

    fun addCity(city: CitiesEntities, citiesMainList: MutableList<CitiesEntities>) {
        this.citiesMainList.value = addCity.invoke(city, citiesMainList)
    }

    fun removeCity(city: CitiesEntities, citiesMainList: MutableList<CitiesEntities>) {
        this.citiesMainList.value = removeCity.invoke(city, citiesMainList)
    }

    fun checkForAllowanceOfAddingCities(maximumCitiesAllowed: Int) {
        this.isAllowedAddingCity.value = allowAddCity.invoke(maximumCitiesAllowed)
    }


}