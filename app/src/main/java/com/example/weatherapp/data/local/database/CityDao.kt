package com.example.weatherapp.data.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.weatherapp.data.local.database.entities.CityEntity

@Dao
interface CityDao {

    @Query("SELECT * FROM city ORDER BY id DESC LIMIT 1")
    suspend fun getCity(): CityEntity?

    @Insert
    suspend fun insertCity(city: CityEntity)

    @Insert
    suspend fun insertMultipleCities(vararg city: CityEntity)

    @Delete
    suspend fun deleteCity(city: CityEntity): Int

    @Update
    suspend fun updateCity(city: CityEntity): Int
}