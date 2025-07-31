package com.example.weatherapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherapp.data.local.database.entities.CityEntity

@Database(entities = [CityEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
}