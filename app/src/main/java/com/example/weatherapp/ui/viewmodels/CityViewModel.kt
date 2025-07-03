package com.example.weatherapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.interfaces.ICityRepository
import com.example.weatherapp.domain.models.WeatherDetailsModel
import com.example.weatherapp.domain.models.DomainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityViewModel @Inject constructor(private val repository: ICityRepository) : ViewModel() {

    private val _cityState = MutableLiveData<DomainState<WeatherDetailsModel>>()
    val cityState: LiveData<DomainState<WeatherDetailsModel>> = _cityState

    var isCityFragmentVisible = MutableLiveData<Boolean>()

    //This method returns the Result State
    fun getCity(query: String) {
        viewModelScope.launch {
            _cityState.value = DomainState.Loading
            val result = repository.getWeatherByCity(query)
            _cityState.value = result
        }
    }
}