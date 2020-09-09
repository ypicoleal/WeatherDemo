package com.example.weatherdemo.models

import kotlin.math.roundToInt

data class CurrentWeather(
    val id: Int,
    val cityName: String,
    val temperature: Int,
    val temperatureMin: Int,
    val temperatureMax: Int,
    val description: String,
    val humidity: Int
)

fun CurrentWeatherResponse.toCurrentWeather() : CurrentWeather {
    return CurrentWeather(
        id = weather?.first()?.id ?: 0,
        cityName = name ?: "",
        temperature = main?.temp?.roundToInt() ?: 0,
        temperatureMin = main?.tempMin?.roundToInt() ?: 0,
        temperatureMax = main?.tempMax?.roundToInt() ?: 0,
        description = weather?.first()?.description ?: "",
        humidity = main?.humidity ?: 0
    )
}