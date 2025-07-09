package com.example.weatherapp.data.local

import com.example.weatherapp.domain.interfaces.IWeatherIconService
import javax.inject.Inject

class WeatherIconServiceImpl @Inject constructor() : IWeatherIconService {
    override fun getIconUrlFromFileName(fileName: String): String {
        return "https://openweathermap.org/img/wn/$fileName@4x.png"
    }
}