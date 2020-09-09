package com.example.weatherdemo.models

import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
    @SerializedName("dt") val dt: Int = 0,
    @SerializedName("coord") val coord: Coord? = null,
    @SerializedName("weather") val weather: List<WeatherItem>? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("cod") val cod: Int = 0,
    @SerializedName("main") val main: Main? = null,
    @SerializedName("clouds") val clouds: Clouds? = null,
    @SerializedName("id") val id: Int = 0,
    @SerializedName("sys") val sys: Sys? = null,
    @SerializedName("base") val base: String? = null,
    @SerializedName("wind") val wind: Wind? = null
)