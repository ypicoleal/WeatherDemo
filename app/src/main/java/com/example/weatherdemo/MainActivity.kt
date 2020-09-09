package com.example.weatherdemo

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weatherdemo.databinding.ActivityMainBinding
import com.example.weatherdemo.ui.main.views.CityWeatherItemView
import com.example.weatherdemo.viewmodels.MainViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import java.util.Locale
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MainViewModel

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val groupAdapter by lazy { GroupAdapter<GroupieViewHolder>() }

    private val locationManager by lazy { this.getSystemService(Context.LOCATION_SERVICE) as LocationManager }

    @ExperimentalStdlibApi
    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MainApplication).applicationComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        lifecycle.addObserver(viewModel)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initUI()
        BuildConfig.APPLICATION_ID
    }

    @ExperimentalStdlibApi
    private fun initUI() {
        viewModel.weatherListData.observe(this, Observer { weathers ->
            groupAdapter.addAll(weathers.map { CityWeatherItemView(it) })
        })

        viewModel.weatherMainData.observe(this, Observer { main ->
            binding.title.text = main.cityName
            binding.mainTemp.text = "${main.temperature}º"
            binding.mainDesciption.text = main.description.capitalize(Locale.US)
            binding.mainHumidityValue.text = "${main.humidity}%"
            binding.refresh.isRefreshing = false
        })

        binding.othersList.adapter = groupAdapter
        binding.refresh.setOnRefreshListener {
            tryGetWeather()
        }

        binding.refresh.isRefreshing = true
        tryGetWeather()
    }

    private fun tryGetWeather() {
        getLastKnownLocation()?.let { viewModel.getWeather(it) }
    }

    private fun getLastKnownLocation(): Location? {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ),
                123
            )
            return null
        }
        val providers: List<String> = locationManager.getProviders(true)
        var bestLocation: Location? = null
        for (provider in providers) {
            val l: Location = locationManager.getLastKnownLocation(provider) ?: continue
            if (bestLocation == null || l.accuracy < bestLocation.accuracy) {
                // Found best last known location:
                bestLocation = l
            }
        }
        return bestLocation
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        tryGetWeather()
    }
}