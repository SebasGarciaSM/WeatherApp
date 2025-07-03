package com.example.weatherapp.ui

object UIWeatherUtils {

    fun String.appendPercentageFormat(): String {
        return "$this%"
    }

    fun Double.appendMetersPerSecondFormat(): String {
        return if (this != 0.0) {
            "$this m/s"
        } else {
            "n/a"
        }
    }

    fun Int.appendDegreeFormat(): String {
        return "$this°"
    }

}