package com.example.weatherapp.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.weatherapp.R
import com.example.weatherapp.ui.viewmodels.CityViewModel
import com.example.weatherapp.utils.WeatherUtils
import com.squareup.picasso.Picasso

class CityFragment : Fragment() {
    private val cityViewModel: CityViewModel by activityViewModels()
    private lateinit var weatherUtils: WeatherUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        weatherUtils = WeatherUtils()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_city, container, false)
        return initFragmentUI(view)
    }

    @SuppressLint("SetTextI18n")
    private fun initFragmentUI(view: View): View {

        val tvCityName = view.findViewById<TextView>(R.id.tvCityName)
        val tvTemp = view.findViewById<TextView>(R.id.tvTemp)
        val tvFeelsLike = view.findViewById<TextView>(R.id.tvFeelsLike)
        val tvTempMax = view.findViewById<TextView>(R.id.tvMaxTemp)
        val tvMinTemp = view.findViewById<TextView>(R.id.tvMinTemp)
        val tvPressure = view.findViewById<TextView>(R.id.tvPressure)
        val tvHumidity = view.findViewById<TextView>(R.id.tvHumidity)
        val tvSeaLevel = view.findViewById<TextView>(R.id.tvSeaLevel)
        val ivWeatherIcon = view.findViewById<ImageView>(R.id.ivWeatherIcon)


        cityViewModel.city.observe(viewLifecycleOwner) { it ->
            tvCityName.text = it.name
            tvTemp.text = weatherUtils.getKelvinToCelsius(it.mainWeather.temp)
            tvFeelsLike.text = weatherUtils.getKelvinToCelsius(it.mainWeather.feelsLike)
            tvTempMax.text = weatherUtils.getKelvinToCelsius(it.mainWeather.tempMax)
            tvMinTemp.text = weatherUtils.getKelvinToCelsius(it.mainWeather.tempMin)
            tvPressure.text = it.mainWeather.pressure.toString()
            tvHumidity.text = weatherUtils.getPercentage(it.mainWeather.humidity)
            tvSeaLevel.text = it.mainWeather.seaLevel.toString()

            val currentWeatherIcon = it.weather.first().icon
            Picasso.get().load("https://openweathermap.org/img/wn/$currentWeatherIcon@4x.png")
                .into(ivWeatherIcon)
        }

        return view
    }
}