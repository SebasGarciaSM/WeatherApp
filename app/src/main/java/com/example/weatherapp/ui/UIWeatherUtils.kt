package com.example.weatherapp.ui

object UIWeatherUtils {

    fun Int.appendPercentageFormat(): String {
        return "$this%"
    }

    fun Double.appendMetersPerSecondFormat(): String {
        if (this != 0.0) {
            return "$this m/s"
        } else {
            return "n/a"
        }
    }

    fun Double.appendDegreeFormat(): String {
        return "$this°"
    }

}