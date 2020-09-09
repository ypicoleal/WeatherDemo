package com.example.weatherdemo.ui.main.views

import com.example.weatherdemo.R
import com.example.weatherdemo.databinding.ItemWeatherBinding
import com.example.weatherdemo.models.CurrentWeather
import com.example.weatherdemo.utils.setWeatherIcon
import com.xwray.groupie.databinding.BindableItem

class CityWeatherItemView(private val weather: CurrentWeather) : BindableItem<ItemWeatherBinding>() {

    override fun getLayout() = R.layout.item_weather

    override fun bind(viewBinding: ItemWeatherBinding, position: Int) {
        viewBinding.cityName = weather.cityName
        viewBinding.currentTemp = "${weather.temperature}º"
        viewBinding.minTemp = "${weather.temperatureMin}º"
        viewBinding.maxTemp = "${weather.temperatureMax}º"
        viewBinding.iconIv.setWeatherIcon(weather.id)
    }
}