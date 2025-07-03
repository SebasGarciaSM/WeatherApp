package com.example.weatherapp.data

import kotlin.math.roundToInt

object DataUtils {
    fun Double.convertKelvinToCelsius(): Int {
        val celsius = this - 273.15.roundToInt()
        return celsius.roundToInt()
    }
}