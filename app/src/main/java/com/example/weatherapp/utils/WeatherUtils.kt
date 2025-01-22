package com.example.weatherapp.utils

class WeatherUtils {

    fun getKelvinToCelsius(kelvin: Float): String {
        val celsius = kelvin - 273.15f
        val roundedCelsius = Math.round(celsius)
        return "$roundedCelsius°C"
    }

    fun getPercentage(value:Int): String {
        return "$value%"
    }

}