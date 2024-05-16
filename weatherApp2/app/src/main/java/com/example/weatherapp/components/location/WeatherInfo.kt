package com.example.weatherapp.components.location

import android.Manifest
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.core.location.component1
import androidx.core.location.component2
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.components.weather.WeatherApi
import com.example.weatherapp.components.weather.WeatherRepository

@Composable
fun WeatherScreen() {
    val viewModel: LocationViewModel = viewModel()
    val location = viewModel.location

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissions ->
            // Check if all requested permissions have been granted
            val allPermissionsGranted = permissions.entries.all { it.value }
            Log.d("StartLocationUpdates", "Start")
            if (allPermissionsGranted) {
                viewModel.startLocationUpdates()
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
        val weatherList by WeatherRepository.getWeatherList(latitude, longitude).collectAsState(initial = emptyList())

        // Render your UI with weatherList
    } else {
        // Render loading or error state
    }
}