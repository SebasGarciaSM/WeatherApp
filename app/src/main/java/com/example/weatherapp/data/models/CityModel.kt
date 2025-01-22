package com.example.weatherapp.data.models

import com.google.gson.annotations.SerializedName

data class CityModel(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("weather") val weather: List<WeatherModel>,
    @SerializedName("main") val mainWeather: MainWeatherModel,
)