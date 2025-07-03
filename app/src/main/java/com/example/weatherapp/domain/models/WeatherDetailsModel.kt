package com.example.weatherapp.domain.models

data class WeatherDetailsModel(
    val name: String,
    val temperatureInCelsius: Int,
    val feelsLikeInCelsius: Int,
    val maxTempInCelsius: Int,
    val minTempInCelsius: Int,
    val pressure: String,
    val humidity: String,
    val seaLevel: String,
    val wind: WindDetailsModel,
    val icon: String,
)
