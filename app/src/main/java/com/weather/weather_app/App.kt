package com.weather.weather_app

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.weather.weather_app.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    companion object {
        lateinit var context: Context
        fun getAppContext(): Context {
            return context
        }
    }


    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listOf(viewModelModule, repositoriesModule, sharedPref, networkModule,
                useCasesModule, databaseModule))
        }
    }





}