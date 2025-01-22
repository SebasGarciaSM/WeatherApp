package com.example.weatherapp.data.repositories

import android.util.Log
import com.example.weatherapp.data.models.CityModel
import com.example.weatherapp.data.network.CityService

class CityRepository {

    private val api = CityService()

    suspend fun getCity(query: String): CityModel? {
        try {
            val response = api.getCity(query)
            return response!!
        } catch (e: Exception) {
            Log.i("Error", e.toString())
        }
        return null
    }

}