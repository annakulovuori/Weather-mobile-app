package com.example.weatherapp.components.location


import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Looper
import android.util.Log
import android.Manifest
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority


class LocationRepository(private val context: Context) {

    // Funktio aloittaa sijaintipäivitykset ja kutsuu callbackia sijaintitiedoilla
    fun startLocationUpdates(callback: (Location?) -> Unit) {
        Log.d("Location", "startLocationUpdates called")

        // Tarkista, onko sijaintiluvat myönnetty
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            Log.d("Location", "Permissions granted, requesting location updates")
            // Luvat myönnetty, jatketaan sijaintipäivitysten pyytämisellä
            val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
            val locationRequest = LocationRequest.Builder(10000L)
                .setPriority(Priority.PRIORITY_HIGH_ACCURACY)// Korkea tarkkuus
                .setIntervalMillis(10000L) // Päivitysväli 10 sekuntia
                .build()

            // Luodaan sijaintipäivityksien käsittelijä
            val locationCallback = object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    Log.d("Location", "Location received: ${locationResult.lastLocation}")
                    // Kutsutaan callbackia saadulla sijaintitiedolla
                    callback(locationResult.lastLocation)
                }
            }
            // Pyydetään sijaintipäivityksiä
            fusedLocationProviderClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper() // Käytä pääsäiettä
            )

        } else {
            Log.d("Location", "Not granted!")
        }
    }
}