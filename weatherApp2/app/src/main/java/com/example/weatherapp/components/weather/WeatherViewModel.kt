package com.example.weatherapp.components.weather

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModelProvider

class WeatherViewModel(
    private val weatherRepository: WeatherRepository
): ViewModel() {
    // private val _temperature = MutableStateFlow<Double>()
    private val _temperature = MutableStateFlow<Double?>(null) // StateFlow to hold weather data
    val temperature = _temperature.asStateFlow() // Expose as StateFlow

    fun getTemperature() {
        viewModelScope.launch {
            try {
                val temperature = weatherRepository.getTemperature(61.49, 23.78) // Assuming this is a suspend function
                _temperature.value = temperature
            } catch (e: Exception) {
                // Handle error if needed
                println("Error fetching temperature: ${e.message}")
            }
        }
    }
}

object WeatherViewModelFactory {
    fun create(): WeatherViewModelFactoryClass {
        val weatherApi = WeatherApiService.create()
        val weatherRepository = WeatherRepository(weatherApi)
        return WeatherViewModelFactoryClass(weatherRepository)
    }
}

class WeatherViewModelFactoryClass(
    private val weatherRepository: WeatherRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WeatherViewModel(weatherRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
