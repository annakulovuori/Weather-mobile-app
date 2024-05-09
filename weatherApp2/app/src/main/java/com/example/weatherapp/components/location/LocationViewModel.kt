package com.example.weatherapp.components.location

import android.app.Application
import android.location.Location
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel


class LocationViewModel(application: Application) : AndroidViewModel(application) {
    private val locationRepository = LocationRepository(application)

    private val _location = mutableStateOf<Location?>(null)
    val location: State<Location?> = _location

    fun startLocationUpdates() {
        Log.d("StartLocationUpdates", "Start")
        locationRepository.startLocationUpdates { location ->
            _location.value = location
        }
    }
}