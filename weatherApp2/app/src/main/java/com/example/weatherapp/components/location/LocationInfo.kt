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
    // Luo viewModel
    val locationViewModel: LocationViewModel = viewModel()
    // Hae sijainti viewModelista
    val location = locationViewModel.location

    // Luo permissionLauncher käsittelemään lupapyynnöt
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissions ->
            // Tarkista, onko kaikki pyydetyt luvat myönnetty
            val allPermissionsGranted = permissions.entries.all { it.value }
            Log.d("StartLocationUpdates", "Start")
            if (allPermissionsGranted) {
                // Käynnistä sijaintipäivitykset, jos kaikki luvat on myönnetty
                locationViewModel.startLocationUpdates()
            }
        }
    )

    // Pyydä tarvittavia lupia, kun LaunchedEffect aktivoituu
    LaunchedEffect(Unit) {
        permissionLauncher.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ))
    }


    // Hae sijaintitiedot
    val locationData = location.value

    if (locationData != null) {
        // Jos sijaintitiedot ovat saatavilla, haetaan säädata
        val (latitude, longitude) = locationData
        weatherViewModel.getWeather(latitude, longitude)

    } else {
        // Tulosta viesti, jos sijaintitiedot ovat null
        println("LocationData is null")
    }
}