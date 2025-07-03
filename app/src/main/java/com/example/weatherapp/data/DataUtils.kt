package com.example.weatherapp.data

object DataUtils {
    fun Float.convertKelvinToCelsius(): Double {
        val celsius = this - 273.15f
        val roundedCelsius = celsius.toDouble()
        return roundedCelsius
    }
}