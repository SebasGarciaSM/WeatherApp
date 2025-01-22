package com.example.weatherapp.utils

class WeatherUtils {

    fun getKelvinToCelsius(kelvin: Float): String {
        val celsius = kelvin - 273.15f
        val roundedCelsius = Math.round(celsius)
        return "$roundedCelsius°C"
    }

    fun getPercentage(value: Int): String {
        return "$value%"
    }

    fun getMetersPerSecond(value: Float): String {
        if(value != 0.0f){
            return "$value m/s"
        } else{
            return "n/a"
        }
    }

    fun getDegrees(value: Float): String {
        return "$value°"
    }

}