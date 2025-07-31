package com.example.weatherapp.data.local.contracts

interface ILocalStorageService {
    suspend fun saveLastSearchedCity(cityName: String)

    suspend fun getLastSearchedCity(): String?
}