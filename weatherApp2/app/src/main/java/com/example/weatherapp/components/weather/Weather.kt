package com.example.weatherapp.components.weather

data class Weather(
    val current: Current,
    val daily: Daily,
    val latitude: Double,
    val longitude: Double,
)