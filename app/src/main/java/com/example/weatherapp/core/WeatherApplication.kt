package com.example.weatherapp.core

import android.app.Application
import com.example.weatherapp.di.databaseModule
import com.example.weatherapp.di.networkModule
import com.example.weatherapp.di.repositoryModule
import com.example.weatherapp.di.serviceModule
import com.example.weatherapp.di.useCasesModule
import com.example.weatherapp.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class WeatherApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@WeatherApplication)
            modules(
                databaseModule,
                networkModule,
                repositoryModule,
                serviceModule,
                useCasesModule,
                viewModelsModule,
            )
        }
    }
}