package com.example.weatherapp.di

import com.example.weatherapp.data.local.contracts.ILocalStorageService
import com.example.weatherapp.domain.usecases.GetLastSearchedCityFromLocalStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {
    @Provides
    @Singleton
    fun provideGetLastSearchedCityFromLocalStorage(localStorageService: ILocalStorageService): GetLastSearchedCityFromLocalStorage {
        return GetLastSearchedCityFromLocalStorage(localStorageService)
    }
}