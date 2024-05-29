package com.example.weatherapp.components.location

import android.Manifest
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.core.location.component1
import androidx.core.location.component2
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.components.weather.WeatherViewModel

@Composable
fun locationInfo(weatherViewModel: WeatherViewModel = viewModel()) {
    val locationViewModel: LocationViewModel = viewModel()
    val location = locationViewModel.location

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissions ->
            // Check if all requested permissions have been granted
            val allPermissionsGranted = permissions.entries.all { it.value }
            Log.d("StartLocationUpdates", "Start")
            if (allPermissionsGranted) {
                locationViewModel.startLocationUpdates()
            }
        }
    )

    LaunchedEffect(Unit) {
        permissionLauncher.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ))
    }

    val locationData = location.value

    if (locationData != null) {
        val (latitude, longitude) = locationData
        weatherViewModel.getWeather(latitude, longitude)

    } else {
        println("LocationData is null")
    }
}