package com.floriaapp.core.repo

import com.floriaapp.core.entity.CitiesEntities
import com.floriaapp.core.entity.CitiesNeeded

interface MainHomeRepo {
    suspend fun getCitiesData(): CitiesNeeded?
    suspend fun addCity(
        city: CitiesEntities
    )

    suspend fun removeCity(
        city: CitiesEntities
    )

    suspend fun getSavedCities():CitiesNeeded

    fun isAllowedAddingCity(numberOfAllowedAddition: Int): Boolean

}