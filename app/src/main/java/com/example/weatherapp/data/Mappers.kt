package com.example.weatherapp.data

import com.example.weatherapp.data.DataUtils.convertKelvinToCelsius
import com.example.weatherapp.data.models.CityModel
import com.example.weatherapp.domain.models.WeatherDetailsModel
import com.example.weatherapp.domain.models.WindDetailsModel

object Mappers {
    fun CityModel.toWeatherDetailsModel(): WeatherDetailsModel {
        return WeatherDetailsModel(
            name = this.name,
            temperatureInCelsius = this.mainWeather.temp.convertKelvinToCelsius(),
            feelsLikeInCelsius = this.mainWeather.feelsLike.convertKelvinToCelsius(),
            maxTempInCelsius = this.mainWeather.tempMax.convertKelvinToCelsius(),
            minTempInCelsius = this.mainWeather.tempMin.convertKelvinToCelsius(),
            pressure = this.mainWeather.pressure.toString(),
            humidity = this.mainWeather.humidity.toString(),
            seaLevel = this.mainWeather.seaLevel.toString(),
            wind = WindDetailsModel(
                speed = this.wind.speed.toDouble(),
                gust = this.wind.gust.toDouble(),
                degrees = this.wind.deg.toDouble()
            ),
            icon = this.weather.first().icon,
        )
    }
}