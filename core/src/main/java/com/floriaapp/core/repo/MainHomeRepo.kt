package com.floriaapp.core.repo

import com.floriaapp.core.entity.CitiesEntities
import com.floriaapp.core.entity.CitiesNeeded

interface MainHomeRepo {
    fun getCitiesData(): CitiesNeeded?
    fun addCity(city: CitiesEntities, citiesMainList: MutableList<CitiesEntities>):CitiesNeeded
    fun removeCity(city: CitiesEntities, citiesMainList: MutableList<CitiesEntities>):CitiesNeeded

}