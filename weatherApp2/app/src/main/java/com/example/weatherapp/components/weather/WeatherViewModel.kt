package com.example.weatherapp.components.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModelProvider

// ViewModel, joka vastaa säädatan hakemisesta ja hallinnasta
class WeatherViewModel(
    private val weatherRepository: WeatherRepository
): ViewModel() {

    // MutableStateFlow, joka sisältää säädatan
    private val _weather = MutableStateFlow<Weather?>(null)
    // Julkinen StateFlow, jota ulkopuoliset eivät voi muokata
    val weather = _weather.asStateFlow()

    // Funktio, joka käynnistää säädatan hakemisen annetuilla koordinaateilla
    fun getWeather(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            try {
                // Hae säädata repositoryn kautta ja päivitä _weather-tila
                //val weatherData = weatherRepository.getWeather(61.234, 23.435)
                val weatherData = weatherRepository.getWeather(latitude, longitude)
                _weather.value = weatherData
            } catch (e: Exception) {
                // Käsittele mahdolliset virheet
                println("Error fetching temperature: ${e.message}")
            }
        }
    }
}

// Objekti, joka luo WeatherViewModelFactoryClass-instanssin
object WeatherViewModelFactory {
    fun create(): WeatherViewModelFactoryClass {
        // Luo WeatherApi-instanssi
        val weatherApi = WeatherApiService.create()
        // Luo WeatherRepository-instanssi
        val weatherRepository = WeatherRepository(weatherApi)
        // Palauta WeatherViewModelFactoryClass-instanssi
        return WeatherViewModelFactoryClass(weatherRepository)
    }
}

// Luokka, joka luo WeatherViewModel-instansseja
class WeatherViewModelFactoryClass(
    private val weatherRepository: WeatherRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Tarkista, onko luokka WeatherViewModel
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WeatherViewModel(weatherRepository) as T
        }
        // Heitä poikkeus, jos luokka ei ole WeatherViewModel
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
