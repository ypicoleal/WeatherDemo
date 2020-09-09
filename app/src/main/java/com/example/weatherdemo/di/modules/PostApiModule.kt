package com.example.weatherdemo.di.modules

import com.example.weatherdemo.retrofit.services.Service
import com.example.weatherdemo.retrofit.services.RetrofitFactory
import dagger.Module
import dagger.Provides

@Module
class PostApiModule {

    @Provides
    fun postApi() = RetrofitFactory.create(Service::class.java)
}