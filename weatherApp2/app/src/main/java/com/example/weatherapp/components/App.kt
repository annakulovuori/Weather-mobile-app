package com.example.weatherapp.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.components.ForecastScreen
import com.example.weatherapp.components.WeatherNowScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun App() {
    // Create a NavController to handle navigation between composables
    val navController = rememberNavController()
    Scaffold (
        bottomBar = { CreateBottomBar(navController = navController)}
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

@Composable
fun CreateBottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBar.Home,
        BottomBar.Forecast
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation (
        modifier = Modifier.background(color = Color.White), // Example: set background color
        backgroundColor = Color(0xFF87CEEB),
        contentColor = Color.White,
    ){
        screens.forEach { screen ->
            AddItem(screen = screen, currentDestination = currentDestination, navController = navController)
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBar,
    currentDestination: NavDestination?,
    navController: NavHostController,
) {
    BottomNavigationItem(
        label = {
                Text(screen.title)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route)
        },
        modifier = Modifier
            .background(Color.Transparent) // Set the background of the item to transparent
            .padding(vertical = 8.dp) // Adjust padding as needed
    )
}
