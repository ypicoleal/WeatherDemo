package com.example.weatherdemo.retrofit.services

import com.example.weatherdemo.models.CurrentWeatherResponse
import io.reactivex.Flowable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET("weather")
    fun getCurrentWeather(@Query("q") q: String): Flowable<Response<CurrentWeatherResponse>>

    @GET("weather")
    fun getCurrentWeatherByLocation(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double): Flowable<Response<CurrentWeatherResponse>>
}