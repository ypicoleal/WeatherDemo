package com.example.weatherdemo.retrofit.services

import com.example.weatherdemo.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitFactory {

    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    private const val CONNECTION_TIMEOUT_IN_SECONDS = 10L
    private const val READ_TIMEOUT_IN_SECONDS = 10L

    private fun builder(): Retrofit.Builder {
        val logging = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        val client = OkHttpClient().newBuilder()
            .addInterceptor {
                val request = it.request()
                val url = request.url.newBuilder()
                    .addEncodedQueryParameter("units", BuildConfig.WEATHER_UNITS)
                    .addQueryParameter("lang", BuildConfig.WEATHER_LANG)
                    .addQueryParameter("appid", BuildConfig.WEATHER_KEY)
                    .build()
                it.proceed(request.newBuilder().url(url).build())
            }
            .addInterceptor(logging)
            .connectTimeout(CONNECTION_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
    }

    fun <T> create(type: Class<T>): T = builder().build().create(type)
}