package com.floriaapp.core.entity

typealias CitiesNeeded = MutableList<CitiesEntities>
data class CitiesEntities(
    val id: Int?=null,
    val name: String?=null,
    val state: Any?=null,
    val country: String?=null,
    val cord: Cord?=null
)

data class Cord(val lon: Double, val lat: Double)