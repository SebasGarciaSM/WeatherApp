package com.example.weatherapp.di

import android.content.Context
import androidx.room.Room
import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.local.database.AppDatabase
import com.example.weatherapp.data.local.database.CityDao
import org.koin.dsl.module

val databaseModule = module {
    single<AppDatabase> {
        Room.databaseBuilder(get(), AppDatabase::class.java, BuildConfig.DATABASE_NAME)
            .build()
    }

    single<CityDao> {
        get<AppDatabase>().cityDao()
    }
}