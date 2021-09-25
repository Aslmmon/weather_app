package com.floriaapp.core.use_cases.main_home

import com.floriaapp.core.repo.MainHomeRepo

class GetCitiesData(private val mainHomeRepo: MainHomeRepo) {
     operator fun invoke() = mainHomeRepo.getCitiesData()
}