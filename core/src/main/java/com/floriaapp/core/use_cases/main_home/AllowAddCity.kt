package com.floriaapp.core.use_cases.main_home

import com.floriaapp.core.repo.MainHomeRepo

class AllowAddCity(private val mainHomeRepo: MainHomeRepo) {
    operator fun invoke(numberOfAllowedAddition: Int) =
        mainHomeRepo.isAllowedAddingCity(numberOfAllowedAddition = numberOfAllowedAddition)
}