package com.example.weatherapp.di

import com.example.weatherapp.domain.usecases.GetLastSearchedCityFromLocalStorage
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val useCasesModule = module {
    singleOf(::GetLastSearchedCityFromLocalStorage)
}