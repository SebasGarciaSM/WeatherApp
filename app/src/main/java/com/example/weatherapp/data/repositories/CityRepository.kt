package com.example.weatherapp.data.repositories

import com.example.weatherapp.data.Mappers.toWeatherDetailsModel
import com.example.weatherapp.data.network.CityApiClient
import com.example.weatherapp.domain.interfaces.ICityRepository
import com.example.weatherapp.domain.models.WeatherDetailsModel
import com.example.weatherapp.domain.models.DomainState
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CityRepository @Inject constructor(private val api: CityApiClient) : ICityRepository {

    //Gets the response, and then returns the API Result State
    override suspend fun getWeatherByCity(cityName: String): DomainState<WeatherDetailsModel> {
        return try {
            val response = api.getCity(cityName)
            if (response.isSuccessful) {
                val city = response.body()
                if (city != null) {
                    DomainState.Success(city.toWeatherDetailsModel())
                } else {
                    DomainState.Error("City not found")
                }
            } else {
                DomainState.Error("City not found")
            }
        } catch (e: IOException) {
            DomainState.Error("Network error: ${e.message}")
        } catch (e: HttpException) {
            DomainState.Error("HTTP error: ${e.message}")
        } catch (e: Exception) {
            DomainState.Error("Unexpected error: ${e.message}")
        }
    }

}