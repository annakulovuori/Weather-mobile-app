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

    private val _weather = MutableStateFlow<Weather?>(null) // StateFlow to hold weather data
    val weather = _weather.asStateFlow() // Expose as StateFlow

    fun getWeather() {
        viewModelScope.launch {
            try {
                val weatherData = weatherRepository.getWeather(61.49, 23.78)
                _weather.value = weatherData
            } catch (e: Exception) {
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
