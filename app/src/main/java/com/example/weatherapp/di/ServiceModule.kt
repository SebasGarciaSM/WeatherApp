package com.example.weatherapp.di

import com.example.weatherapp.data.local.DataStoreLocalStorageServiceImpl
import com.example.weatherapp.data.local.RoomDatabaseLocalStorageImpl
import com.example.weatherapp.data.local.WeatherIconServiceImpl
import com.example.weatherapp.data.local.contracts.ILocalStorageService
import com.example.weatherapp.domain.interfaces.IWeatherIconService
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val serviceModule = module {
    singleOf(::WeatherIconServiceImpl) { bind<IWeatherIconService>() }

    //singleOf(::DataStoreLocalStorageServiceImpl) { bind<ILocalStorageService>() }
    single<ILocalStorageService> { RoomDatabaseLocalStorageImpl(get()) }
}