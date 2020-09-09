package com.example.weatherdemo.models

import com.google.gson.annotations.SerializedName

data class Wind (
    @SerializedName("deg") val deg: Float = 0f,
    @SerializedName("speed") val speed: Float = 0f
)