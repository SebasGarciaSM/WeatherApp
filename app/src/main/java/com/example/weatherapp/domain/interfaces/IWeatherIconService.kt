package com.example.weatherapp.domain.interfaces

interface IWeatherIconService {
    fun getIconUrlFromFileName(fileName: String): String
}