package com.example.weatherapp.components

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.components.ForecastScreen
import com.example.weatherapp.components.WeatherNowScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun App() {
    // Create a NavController to handle navigation between composables
    val navController = rememberNavController()
    Scaffold (
        bottomBar = {}
    ){
        NavGraphBuilder(navController = navController)
    }


}
@Composable
fun NavGraphBuilder(navController: NavHostController) {
    // Define a navigation host that controls the navigation within this composable
    NavHost(navController = navController, startDestination = "WeatherNowScreen") {
        composable(BottomBar.Home.route) {
            WeatherNowScreen()
        }
        composable(BottomBar.Forecast.route) {
            ForecastScreen()
        }
    }
}
