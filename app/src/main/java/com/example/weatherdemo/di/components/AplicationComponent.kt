package com.example.weatherdemo.di.components

import com.example.weatherdemo.MainActivity
import com.example.weatherdemo.MainApplication
import com.example.weatherdemo.di.modules.ApplicationModule
import com.example.weatherdemo.di.modules.FactoryModule
import com.example.weatherdemo.di.modules.HomeViewModelModule
import com.example.weatherdemo.di.modules.PostApiModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, PostApiModule::class, FactoryModule::class, HomeViewModelModule::class])
interface ApplicationComponent {

    fun inject(application: MainApplication)

    fun inject(activity: MainActivity)
}