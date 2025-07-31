package com.example.weatherapp.data.local

import com.example.weatherapp.data.local.contracts.ILocalStorageService
import com.example.weatherapp.data.local.database.CityDao
import com.example.weatherapp.data.local.database.entities.CityEntity
import javax.inject.Inject

class RoomDatabaseLocalStorageImpl @Inject constructor(
    private val cityDao: CityDao
) : ILocalStorageService {
    override suspend fun saveLastSearchedCity(cityName: String) {
        val lastCity = cityDao.getCity()

        if (lastCity?.cityName == cityName) {
            return
        }

        val cityEntity = CityEntity(cityName)
        cityDao.insertCity(cityEntity)
    }

    override suspend fun getLastSearchedCity(): String? {
        val cityEntity = cityDao.getCity()
        return cityEntity?.cityName
    }

}