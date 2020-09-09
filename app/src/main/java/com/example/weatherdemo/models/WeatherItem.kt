package com.example.weatherdemo.models

import com.google.gson.annotations.SerializedName

data class WeatherItem (
    @SerializedName("icon") val icon: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("main") val main: String? = null,
    @SerializedName("id") val id: Int = 0
)