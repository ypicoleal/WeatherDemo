package com.example.weatherdemo.di.modules

import androidx.lifecycle.ViewModelProvider
import com.example.weatherdemo.di.factory.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class FactoryModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}