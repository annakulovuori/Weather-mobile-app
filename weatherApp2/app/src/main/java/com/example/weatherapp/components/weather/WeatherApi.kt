package com.example.weatherapp.components.weather

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface WeatherApi {

    // Määrittelee GET-pyynnön, joka hakee sääennusteen
    @GET("forecast")
    suspend fun getWeatherData(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("current") current: String = "temperature_2m",
        @Query("daily") daily: String = "weather_code,temperature_2m_max,temperature_2m_min",
        @Query("hourly") hourly: String = "weather_code,temperature_2m",
        @Query("timezone") timezone: String = "auto"
    ): Weather

}

// Singleton-objekti, joka luo ja tarjoaa WeatherApi-instanssin
object WeatherApiService {
    private const val BASE_URL = "https://api.open-meteo.com/v1/"

    // Funktio, joka luo Retrofit-instanssin ja palauttaa WeatherApi-rajapinnan
    fun create(): WeatherApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) //Gson-muunnin JSON-datan käsittelyyn
            .build()

        return retrofit.create(WeatherApi::class.java)
    }
}