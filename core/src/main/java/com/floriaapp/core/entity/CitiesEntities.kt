package com.floriaapp.core.entity

data class CitiesEntities(
    val id: Int,
    val name: String,
    val state: Any,
    val country: String,
    val cord: Cord
)

data class Cord(val lon: Double, val lat: Double)