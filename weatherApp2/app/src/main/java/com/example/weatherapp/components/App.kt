package com.example.weatherapp.components

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.components.ForecastScreen
import com.example.weatherapp.components.WeatherNowScreen

@Composable
fun App() {
    // Create a NavController to handle navigation between composables
    val navController = rememberNavController()

    // Define a navigation host that controls the navigation within this composable
    NavHost(navController = navController, startDestination = "WeatherNowScreen") {
        composable("WeatherNowScreen") {
            WeatherNowScreen(navController)
        }
        composable("ForecastScreen") {
            ForecastScreen(navController)
        }
    }

}