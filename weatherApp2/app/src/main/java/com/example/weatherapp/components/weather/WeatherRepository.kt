package com.example.weatherapp.components.weather

import retrofit2.HttpException
import java.io.IOException

// Luokka, joka vastaa säädatan hakemisesta API:n kautta
class WeatherRepository(
    private val api: WeatherApi
) {
    // Funktio, joka hakee säätiedot annetulle sijainnille
    suspend fun getWeather(latitude: Double, longitude: Double): Weather? {
        var weatherFromApi: Weather? = null // Muuttuja säätiedon tallentamiseen
        try {
            // Yritetään hakea säätiedot API:n kautta
            weatherFromApi = api.getWeatherData(latitude, longitude)
            println(weatherFromApi)
        } catch (e: IOException) {
            // Käsitellään verkkovirheet
            println("Error loading weather")
        } catch (e: HttpException) {
            // Käsitellään HTTP-virheet
            println("Error loading weather")
        }
        return weatherFromApi // Palautetaan haettu säätieto tai null
        }
    }