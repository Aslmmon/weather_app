package com.floriaapp.core.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

typealias CitiesNeeded = MutableList<CitiesEntities>

const val TABLE_NAME = "CityEntity"

@Entity(tableName = TABLE_NAME)
data class CitiesEntities(
    @PrimaryKey(autoGenerate = true) val cityEntityId: Int ,
    val name: String,
    var WeatherData: List<DateWithData>? = null
)

