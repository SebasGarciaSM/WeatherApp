package com.example.weatherapp.data.network

import com.example.weatherapp.core.Globals
import com.example.weatherapp.data.models.CityModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CityApiClient {

    //GET CITY
    @GET("data/2.5/weather")
    suspend fun getCity(
        @Query("q") query: String,
        @Query("appid") apiKey: String = Globals.API_KEY
    ): Response<CityModel>
}