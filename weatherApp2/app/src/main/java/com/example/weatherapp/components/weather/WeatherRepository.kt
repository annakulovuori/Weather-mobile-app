package com.example.weatherapp.components.weather

import retrofit2.HttpException
import java.io.IOException


class WeatherRepository(
    private val api: WeatherApi
) {
    suspend fun getWeather(latitude: Double, longitude: Double): Weather? {
        var weatherFromApi: Weather? = null
        try {
            weatherFromApi = api.getWeatherData(latitude, longitude)
            println(weatherFromApi)
        } catch (e: IOException) {
            println("Error loading weather")
        } catch (e: HttpException) {
            println("Error loading weather")
        }
        return weatherFromApi
        }
    }