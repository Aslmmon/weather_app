package com.floriaapp.core.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.floriaapp.core.db.dao.ForecastDAO
import com.floriaapp.core.db.dao.MainCitiesListDAO
import com.floriaapp.core.entity.CitiesEntities
import com.floriaapp.core.entity.DateConverter
import com.floriaapp.core.entity.DateWithData

@Database(entities = [DateWithData::class,CitiesEntities::class], version = 1,exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mainListDAO(): MainCitiesListDAO
    abstract fun foreCastDAO(): ForecastDAO

}