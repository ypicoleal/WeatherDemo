package com.example.weatherdemo.di.modules

import androidx.lifecycle.ViewModel
import com.example.weatherdemo.di.scopes.ViewModelKey
import com.example.weatherdemo.viewmodels.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HomeViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}