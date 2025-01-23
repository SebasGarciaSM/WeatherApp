package com.example.weatherapp.utils

//This class contains all the States that the API could return
sealed class ApiState<out T> {
    data class Success<out T>(val data: T) : ApiState<T>()
    data class Error(val message: String) : ApiState<Nothing>()
    data object Loading : ApiState<Nothing>()
}