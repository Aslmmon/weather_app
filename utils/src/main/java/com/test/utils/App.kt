package com.test.utils

import android.app.Application
import android.content.Context
import com.test.utils.Common.di.networkModule
import com.test.utils.Common.di.viewModelModule
import com.test.utils.Common.di.repositoriesModule
import com.test.utils.Common.di.sharedPref
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
            modules(listOf(viewModelModule, repositoriesModule, sharedPref, networkModule))
        }
    }


}