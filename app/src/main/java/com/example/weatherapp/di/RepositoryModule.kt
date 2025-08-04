package com.example.weatherapp.di

import com.example.weatherapp.data.repositories.CityRepository
import com.example.weatherapp.domain.interfaces.ICityRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::CityRepository) { bind<ICityRepository>() }
}