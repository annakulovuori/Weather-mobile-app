package com.example.weatherapp.components.weather

data class WeatherInfo(
    //[0] = today, [1] = tomorrow etc etc
    val weatherDataPerDay: Map<Int, List<weatherData>>,
    val currentWeatherData: weatherData?
)
