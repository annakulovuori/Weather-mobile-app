package com.example.weatherapp.components.weather

data class Weather(
    val current: Current,
    val daily: Daily,
    val hourly: Hourly,
    val latitude: Double,
    val longitude: Double
)