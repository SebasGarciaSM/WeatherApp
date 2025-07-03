package com.example.weatherapp.domain.models

data class WeatherDetailsModel(
    val name: String,
    val temperatureInCelsius: Double,
    val feelsLikeInCelsius: Double,
    val maxTempInCelsius: Double,
    val minTempInCelsius: Double,
    val pressure: String,
    val humidity: String,
    val seaLevel: String,
    val wind: WindDetailsModel,
    val icon: String,
)
