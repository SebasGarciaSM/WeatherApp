package com.example.weatherapp.di

import com.example.weatherapp.data.local.DataStoreLocalStorageServiceImpl
import com.example.weatherapp.data.local.RoomDatabaseLocalStorageImpl
import com.example.weatherapp.data.local.WeatherIconServiceImpl
import com.example.weatherapp.data.local.contracts.ILocalStorageService
import com.example.weatherapp.domain.interfaces.IWeatherIconService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {

    @Binds
    @Singleton
    abstract fun bindWeatherIconService(implementation: WeatherIconServiceImpl): IWeatherIconService

    @Binds
    @Singleton
    abstract fun bindDataStoreLocalStorageService(implementation: RoomDatabaseLocalStorageImpl): ILocalStorageService
}