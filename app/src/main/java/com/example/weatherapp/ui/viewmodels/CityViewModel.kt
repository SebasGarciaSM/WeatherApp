package com.example.weatherapp.ui.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.interfaces.ICityRepository
import com.example.weatherapp.domain.models.WeatherDetailsModel
import com.example.weatherapp.domain.models.DomainState
import com.example.weatherapp.domain.usecases.GetLastSearchedCityFromLocalStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityViewModel @Inject constructor(
    private val repository: ICityRepository,
    private val getLastSearchedCityFromLocalStorage: GetLastSearchedCityFromLocalStorage,
) : ViewModel() {

    private val _cityState = mutableStateOf<DomainState<WeatherDetailsModel>>(DomainState.Idle)
    val cityState: State<DomainState<WeatherDetailsModel>>
        get() = _cityState

    private val _city = mutableStateOf("")
    val city: State<String>
        get() = _city

    //This method returns the Result State
    fun getCity() {
        viewModelScope.launch {
            _cityState.value = DomainState.Loading
            val result = repository.getWeatherByCity(_city.value)
            _cityState.value = result
        }
    }

    fun search(query: String) {
        _city.value = query
    }

    fun onCleanQuery() {
        _city.value = "";
    }

    fun getLastSearchCity() {
        viewModelScope.launch {
            when (val city = getLastSearchedCityFromLocalStorage()) {
                is DomainState.Success -> {
                    city.data?.let {
                        _city.value = it
                        getCity()
                    }
                }

                else -> {}
            }
        }
    }
}