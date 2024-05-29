package com.example.weatherapp.components.location

import android.app.Application
import android.location.Location
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel


class LocationViewModel(application: Application) : AndroidViewModel(application) {
    private val locationRepository = LocationRepository(application)

    // Muuttuva tila sijainnille, aluksi null
    private val _location = mutableStateOf<Location?>(null)
    // Julkinen tila, johon ei voi suoraan kirjoittaa, vaan se voidaan vain lukea
    val location: State<Location?> = _location

    // Funktio, joka käynnistää sijaintipäivitykset
    fun startLocationUpdates() {
        Log.d("StartLocationUpdates", "Start")
        // Käynnistetään sijaintipäivitykset locationRepositoryn kautta
        locationRepository.startLocationUpdates { location ->
            // Päivitetään sijaintitieto
            _location.value = location
        }
    }
}