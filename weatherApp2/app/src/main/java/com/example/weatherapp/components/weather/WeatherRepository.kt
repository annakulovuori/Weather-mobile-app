package com.example.weatherapp.components.weather

import retrofit2.HttpException
import java.io.IOException


class WeatherRepository(
    private val api: WeatherApi
) {
    suspend fun getTemperature(latitude: Double, longitude: Double): Double? {
        var temperatureFromApi: Double? = null
            try {
            temperatureFromApi = api.getWeatherData(latitude = latitude, longitude = longitude).current.temperature_2m
        } catch (e: IOException) {
            println("Error loading weather")
        } catch (e: HttpException) {
            println("Error loading weather")
        }
        return temperatureFromApi
        }
    }