package com.floriaapp.core.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.floriaapp.core.db.dao.ForecastDAO
import com.floriaapp.core.db.dao.MainCitiesListDAO
import com.floriaapp.core.entity.*

@Database(entities = [DateWithData::class, CitiesEntities::class], version = 8, exportSchema = false)
@TypeConverters(MyTypeConverters::class,DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mainListDAO(): MainCitiesListDAO
    abstract fun foreCastDAO(): ForecastDAO

}