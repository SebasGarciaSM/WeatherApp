package com.example.weatherapp.data.repositories

import com.example.weatherapp.domain.interfaces.ICityRepository
import com.example.weatherapp.domain.models.DomainState
import com.example.weatherapp.domain.models.WeatherDetailsModel
import com.example.weatherapp.domain.models.WindDetailsModel

class MockRepository() : ICityRepository {
    override suspend fun getWeatherByCity(cityName: String): DomainState<WeatherDetailsModel> {
        return DomainState.Success(
            WeatherDetailsModel(
                name = cityName,
                temperatureInCelsius = 50,
                feelsLikeInCelsius = 80,
                maxTempInCelsius = 50,
                minTempInCelsius = 50,
                pressure = "50",
                humidity = "50",
                seaLevel = "50",
                wind = WindDetailsModel(
                    speed = 50.0,
                    gust = 50.0,
                    degrees = 50,
                ),
                icon = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.flaticon.com%2Ffree-icon%2Fexample_5650380&psig=AOvVaw1ihAnFILIAGQY0s8lFq9Dn&ust=1751484730924000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCIjvw5uznI4DFQAAAAAdAAAAABAE",
            )
        )
    }
}