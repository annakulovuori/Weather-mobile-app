package com.example.weatherapp.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBar (
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomBar (
        route = "WeatherNowScreen",
        title = "Home",
        icon = Icons.Default.Home
    )
    object Forecast : BottomBar (
        route = "ForecastScreen",
        title = "week",
        icon = Icons.Default.DateRange
    )

}