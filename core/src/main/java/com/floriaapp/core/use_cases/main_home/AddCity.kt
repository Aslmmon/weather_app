package com.floriaapp.core.use_cases.main_home

import com.floriaapp.core.entity.CitiesEntities
import com.floriaapp.core.repo.MainHomeRepo

class AddCity(private val mainHomeRepo: MainHomeRepo) {
    operator fun invoke(city: CitiesEntities, citiesMainList: MutableList<CitiesEntities>) = mainHomeRepo.addCity(city,citiesMainList)
}