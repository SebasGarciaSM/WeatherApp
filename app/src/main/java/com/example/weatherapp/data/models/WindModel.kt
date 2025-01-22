package com.example.weatherapp.data.models

import com.google.gson.annotations.SerializedName

data class WindModel(
    @SerializedName("speed") val speed: Float,
    @SerializedName("deg") val deg: Float,
    @SerializedName("gust") val gust: Float,
)