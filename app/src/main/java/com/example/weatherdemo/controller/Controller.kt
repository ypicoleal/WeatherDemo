package com.example.weatherdemo.controller

import com.example.weatherdemo.models.CurrentWeatherResponse
import com.example.weatherdemo.retrofit.services.Service
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class Controller @Inject constructor(
    private val service: Service
) {

    fun getWeather(cities: List<String>): Flowable<List<CurrentWeatherResponse>> {
        val weathers = cities.map { getWeather(it) }.toTypedArray()
        return Flowable.combineLatest(weathers) { results ->
            results.mapNotNull { it as? CurrentWeatherResponse }
        }
    }

    fun getWeather(location: Pair<Double, Double>): Flowable<CurrentWeatherResponse?> {
        return service.getCurrentWeatherByLocation(location.first, location.second)
            .subscribeOn(Schedulers.io())
            .map { response ->
                response.body()
            }
            .onErrorReturn {
                null
            }
    }

    private fun getWeather(q: String): Flowable<CurrentWeatherResponse?> {
        return service.getCurrentWeather(q)
            .subscribeOn(Schedulers.io())
            .map { response ->
                response.body()
            }
            .onErrorReturn {
                null
            }
    }
}