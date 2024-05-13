package com.example.weatherapp.components.weather

import com.example.weatherapp.components.location.locationOutput
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET
    suspend fun getWeatherData(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ): Weather

    companion object {
        const val BASE_URL = "https://api.open-meteo.com/v1/forecast?current=temperature_2m,weather_code&daily=weather_code,temperature_2m_max,temperature_2m_min&wind_speed_unit=ms&timezone=auto"
    }
}