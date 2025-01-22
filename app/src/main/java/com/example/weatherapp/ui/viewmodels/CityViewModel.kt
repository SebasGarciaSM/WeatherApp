package com.example.weatherapp.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.models.CityModel
import com.example.weatherapp.data.models.WeatherModel
import com.example.weatherapp.data.repositories.CityRepository
import kotlinx.coroutines.launch

class CityViewModel : ViewModel() {

    private val repository = CityRepository()

    val city = MutableLiveData<CityModel>()
    val icon = MutableLiveData<WeatherModel>()
    val isLoading = MutableLiveData<Boolean>()
    val isCityFragmentVisible = MutableLiveData<Boolean>()

    fun getCity(query: String) {
        try {
            viewModelScope.launch {

                isLoading.postValue(true)

                val cityResult = repository.getCity(query)

                if (cityResult != null) {
                    city.postValue(cityResult!!)
                    isLoading.postValue(false)
                    isCityFragmentVisible.postValue(true)
                }
            }
        } catch (e: Exception) {
            isLoading.postValue(false)
            Log.i("Error", e.toString())
        }
    }
}