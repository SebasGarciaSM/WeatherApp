package com.example.weatherapp.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.weatherapp.databinding.FragmentCityBinding
import com.example.weatherapp.ui.viewmodels.CityViewModel
import com.example.weatherapp.ui.views.MainActivity.Companion.CITY_NAME
import com.example.weatherapp.ui.views.dataStore
import com.example.weatherapp.utils.ApiState
import com.example.weatherapp.utils.WeatherUtils
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

class CityFragment : Fragment() {
    private val cityViewModel: CityViewModel by activityViewModels()

    private lateinit var weatherUtils: WeatherUtils
    private lateinit var binding: FragmentCityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        weatherUtils = WeatherUtils()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCityBinding.inflate(inflater, container, false)

        lifecycleScope.launch {
            dataStore.data.collect { preferences ->
                preferences[stringPreferencesKey(CITY_NAME)]
            }
        }

        initFragmentUI()

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun initFragmentUI() {
        cityViewModel.cityState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is ApiState.Loading -> {
                    //When data is Loading, UI shows Progress Bar

                    binding.progressBar.visibility = View.VISIBLE
                }

                is ApiState.Success -> {
                    //When data is successfully received, the Progress Bar and Fragment need to be hidden
                    binding.progressBar.visibility = View.GONE
                    cityViewModel.isCityFragmentVisible.postValue(true)


                    //When data is successfully received, it gets assign to all the fields in the UI
                    val city = result.data
                    binding.tvCityName.text = city.name
                    binding.tvTemp.text = weatherUtils.getKelvinToCelsius(city.mainWeather.temp)
                    binding.tvFeelsLike.text =
                        weatherUtils.getKelvinToCelsius(city.mainWeather.feelsLike)
                    binding.tvMaxTemp.text =
                        weatherUtils.getKelvinToCelsius(city.mainWeather.tempMax)
                    binding.tvMinTemp.text =
                        weatherUtils.getKelvinToCelsius(city.mainWeather.tempMin)
                    binding.tvPressure.text = city.mainWeather.pressure.toString()
                    binding.tvHumidity.text = weatherUtils.getPercentage(city.mainWeather.humidity)
                    binding.tvSeaLevel.text = city.mainWeather.seaLevel.toString()

                    binding.tvSpeed.text = weatherUtils.getMetersPerSecond(city.wind.speed)
                    binding.tvGust.text = weatherUtils.getMetersPerSecond(city.wind.gust)
                    binding.tvDegrees.text = weatherUtils.getDegrees(city.wind.deg)

                    val currentWeatherIcon = city.weather.first().icon
                    Picasso.get()
                        .load("https://openweathermap.org/img/wn/$currentWeatherIcon@4x.png")
                        .into(binding.ivWeatherIcon)

                    //This coroutine saves the data into DataStore
                    lifecycleScope.launch {
                        saveCityName(city.name)
                    }
                }

                is ApiState.Error -> {
                    //When data returns an error,
                    //the Progress Bar is hidden and the UI shows a Toast with the error message
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(context, result.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    //Access to the Context of the Activity
    private val dataStore by lazy {
        requireContext().dataStore
    }

    //Saves City Name on DataStore
    private suspend fun saveCityName(value: String) {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey(CITY_NAME)] = value
        }
    }
}