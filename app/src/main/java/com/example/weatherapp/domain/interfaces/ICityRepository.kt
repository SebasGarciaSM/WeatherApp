package com.example.weatherapp.domain.interfaces

import com.example.weatherapp.domain.models.DomainState
import com.example.weatherapp.domain.models.WeatherDetailsModel

interface ICityRepository {
    suspend fun getWeatherByCity(cityName: String): DomainState<WeatherDetailsModel>
}