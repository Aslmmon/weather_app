package com.floriaapp.core.use_cases.main_home

import com.floriaapp.core.repo.MainHomeRepo

class GetSavedCities(private val mainHomeRepo: MainHomeRepo) {
    suspend operator fun invoke() = mainHomeRepo.getSavedCities()
}