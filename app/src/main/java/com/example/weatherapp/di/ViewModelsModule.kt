package com.example.weatherapp.di

import com.example.weatherapp.ui.viewmodels.CityViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelsModule = module {
    //viewModelOf(::CityViewModel)
    viewModel<CityViewModel> { CityViewModel(get(), get()) }
}