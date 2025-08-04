package com.example.weatherapp.di

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.network.CityApiClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<CityApiClient> {
        get<Retrofit>().create(CityApiClient::class.java)
    }
}