package com.example.weatherdemo.models

import com.google.gson.annotations.SerializedName

data class Coord(
    @SerializedName("lon") val lon: Float = 0f,
    @SerializedName("lat") val lat:Float = 0f
)