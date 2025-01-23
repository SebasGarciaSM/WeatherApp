package com.example.weatherapp.data.repositories

import com.example.weatherapp.data.models.CityModel
import com.example.weatherapp.data.network.CityService
import com.example.weatherapp.utils.ApiState
import retrofit2.HttpException
import java.io.IOException

class CityRepository() {

    private val api = CityService()

    //Gets the response, and then returns the API Result State
    suspend fun getCity(query: String): ApiState<CityModel> {
        return try {
            val response = api.getCity(query)
            if (response.isSuccessful) {
                val city = response.body()
                if (city != null) {
                    ApiState.Success(city)
                } else {
                    ApiState.Error("City not found")
                }
            } else {
                ApiState.Error("City not found")
            }
        } catch (e: IOException) {
            ApiState.Error("Network error: ${e.message}")
        } catch (e: HttpException) {
            ApiState.Error("HTTP error: ${e.message}")
        } catch (e: Exception) {
            ApiState.Error("Unexpected error: ${e.message}")
        }
    }

}