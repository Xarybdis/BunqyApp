package com.example.bunqyapp

import android.app.Application
import com.example.bunqyapp.di.applicationModule
import com.example.bunqyapp.di.networkModule
import com.example.bunqyapp.di.viewModelModule
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApplication : Application() {
    /**
     * We start koin from here, thus, it has application's life cycle and also context.
     * We can add as many modules as we want for Koin.
     */
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        startKoin {
            androidLogger()
            androidContext(this@BaseApplication)
            modules(listOf(networkModule, applicationModule, viewModelModule))
        }

    }
}