package com.example.weatherdemo.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.weatherdemo.R

fun ImageView.setWeatherIcon(weatherCode: Int) {
    val icon = when {
        weatherCode / 100 == 2 -> R.drawable.ic_storm_weather
        weatherCode / 100 == 3 -> R.drawable.ic_rainy_weather
        weatherCode / 100 == 5 -> R.drawable.ic_rainy_weather
        weatherCode / 100 == 6 -> R.drawable.ic_rainy_weather
        weatherCode == 800 -> R.drawable.ic_clear_day
        weatherCode == 801 -> R.drawable.ic_few_clouds
        weatherCode == 803 -> R.drawable.ic_broken_clouds
        weatherCode / 100 == 8 -> R.drawable.ic_cloudy_weather
        else -> null
    }
    Glide.with(context).load(icon).into(this)

}