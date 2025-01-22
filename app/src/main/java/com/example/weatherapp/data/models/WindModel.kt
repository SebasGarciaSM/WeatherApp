package com.example.weatherapp.data.models

import com.google.gson.annotations.SerializedName

data class WindModel(
    @SerializedName("speed") val speed: Float,
    @SerializedName("deg") val deg: Int,
    @SerializedName("gust") val gust: Float,
)