package com.weather.weather_app.di

import androidx.room.Room
import com.floriaapp.core.db.database.AppDatabase
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "weather_database")
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<AppDatabase>().mainListDAO() }
    single { get<AppDatabase>().foreCastDAO() }

}