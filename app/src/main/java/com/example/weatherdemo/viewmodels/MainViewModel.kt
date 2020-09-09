package com.example.weatherdemo.viewmodels

import android.location.Location
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.example.weatherdemo.controller.Controller
import com.example.weatherdemo.models.CurrentWeather
import com.example.weatherdemo.models.toCurrentWeather
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainViewModel @Inject constructor(private val controller: Controller) : ViewModel(),
    LifecycleObserver {

    private var disposable: CompositeDisposable? = null

    private val cities = listOf("Montevideo", "Londres", "San Pablo", "Buenos Aires", "Munich" )

    val weatherListData: MutableLiveData<List<CurrentWeather>> = MutableLiveData()
    val weatherMainData: MutableLiveData<CurrentWeather> = MutableLiveData()

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        disposable?.dispose()
        disposable = null
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        disposable?.dispose()
        disposable = null
    }

    fun getWeather(location: Location) {
        if (disposable == null) disposable = CompositeDisposable()

        val locacationPair = Pair(location.latitude, location.longitude)
        disposable?.add(controller.getWeather(cities)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    weatherListData.postValue(response.map { it.toCurrentWeather() })
                },
                { error ->
                    Log.e(javaClass.simpleName, error.toString())
                }
            ))
        disposable?.add(controller.getWeather(locacationPair)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    weatherMainData.postValue(response?.toCurrentWeather())
                },
                { error ->
                    Log.e(javaClass.simpleName, error.toString())
                }
            ))
    }
}