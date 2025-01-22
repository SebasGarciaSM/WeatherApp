package com.example.weatherapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.models.CityModel
import com.example.weatherapp.data.repositories.CityRepository
import com.example.weatherapp.utils.ApiState
import kotlinx.coroutines.launch

class CityViewModel : ViewModel() {

    private val repository = CityRepository()

    private val _cityState = MutableLiveData<ApiState<CityModel>>()
    val cityState: LiveData<ApiState<CityModel>> = _cityState

    var isCityFragmentVisible = MutableLiveData<Boolean>()

    fun getCity(query: String) {
        viewModelScope.launch {
            _cityState.value = ApiState.Loading
            val result = repository.getCity(query)
            _cityState.value = result
        }
    }
}