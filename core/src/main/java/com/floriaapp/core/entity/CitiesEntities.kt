package com.floriaapp.core.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

typealias CitiesNeeded = MutableList<CitiesEntities>

const val TABLE_NAME = "CityEntity"
@Entity(tableName = TABLE_NAME)
@Parcelize
data class CitiesEntities(
    @PrimaryKey val id: Int,
    val name: String,
    var WeatherData: @RawValue List<DateWithData>? = null
) : Parcelable

