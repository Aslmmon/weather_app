package com.floriaapp.core.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

typealias CitiesNeeded = MutableList<CitiesEntities>
const val TABLE_NAME = "CityEntity"

@Entity(tableName = TABLE_NAME)
data class CitiesEntities(
    @PrimaryKey val id: Int?=null,
    val name: String?=null
)

