package com.example.weatherapp.domain.usecases

import com.example.weatherapp.data.local.contracts.ILocalStorageService
import com.example.weatherapp.domain.models.DomainState

class GetLastSearchedCityFromLocalStorage(
    private val localStorageService: ILocalStorageService
) {
    suspend operator fun invoke(): DomainState<String?> =
        DomainState.Success(localStorageService.getLastSearchedCity())
}