package com.floriaapp.core.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.floriaapp.core.db.dao.ForecastDAO
import com.floriaapp.core.db.dao.MainCitiesListDAO
import com.floriaapp.core.entity.*

@Database(entities = [CitiesEntities::class], version = 10, exportSchema = false)
@TypeConverters(MainObjectConverter::class, ListsDataConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mainListDAO(): MainCitiesListDAO
    abstract fun foreCastDAO(): ForecastDAO

}