package com.example.weatherdemo

import android.app.Application
import com.example.weatherdemo.di.components.ApplicationComponent
import com.example.weatherdemo.di.components.DaggerApplicationComponent
import com.example.weatherdemo.di.modules.ApplicationModule

class MainApplication : Application() {

    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}