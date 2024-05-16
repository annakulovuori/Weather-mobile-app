package com.example.weatherapp.components.weather


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException


class WeatherRepository(
    private val api: WeatherApi
) {
    suspend fun getWeatherList(latitude: Double, longitude: Double): Flow<Result<List<Weather>>> {
        return flow {
            val weatherFromApi = try {
                api.getWeatherData(latitude = latitude, longitude = longitude)
            } catch (e: IOException) {
                emit(Result.Error(data = null, message = "Error loading weather"))
                return@flow
            } catch (e: HttpException) {
                emit(Result.Error(data = null, message = "Error loading weather"))
                return@flow
            }
        }
    }
}