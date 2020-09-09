package com.example.weatherdemo.models

import com.google.gson.annotations.SerializedName

data class Main (
    @SerializedName("temp") val temp: Float = 0f,
    @SerializedName("temp_min") val tempMin: Float = 0f,
    @SerializedName("grnd_level") val grndLevel: Float = 0f,
    @SerializedName("humidity") val humidity: Int = 0,
    @SerializedName("pressure") val pressure: Float = 0f,
    @SerializedName("sea_level") val seaLevel: Float = 0f,
    @SerializedName("temp_max") val tempMax: Float = 0f
)