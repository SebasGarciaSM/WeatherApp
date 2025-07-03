package com.example.weatherapp.domain.models

//This class contains all the States that the API could return
sealed class DomainState<out T> {
    data class Success<out T>(val data: T) : DomainState<T>()
    data class Error(val message: String) : DomainState<Nothing>()
    data object Loading : DomainState<Nothing>()
}