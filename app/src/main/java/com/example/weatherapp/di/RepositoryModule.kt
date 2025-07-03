package com.example.weatherapp.di

import com.example.weatherapp.data.repositories.CityRepository
import com.example.weatherapp.domain.interfaces.ICityRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCityRepository(implementation: CityRepository): ICityRepository
}