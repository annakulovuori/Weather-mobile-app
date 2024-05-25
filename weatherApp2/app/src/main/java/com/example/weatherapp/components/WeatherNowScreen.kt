package com.example.weatherapp.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.components.weather.WeatherRepository
import com.example.weatherapp.components.weather.WeatherViewModel
import com.example.weatherapp.components.weather.WeatherViewModelFactory
import kotlinx.coroutines.flow.StateFlow

@Composable
fun WeatherNowScreen() {
    val viewModel: WeatherViewModel = viewModel(factory = WeatherViewModelFactory.create())
    val temperature = viewModel.temperature.collectAsState().value

    viewModel.getTemperature()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column {
            Box(
                modifier = Modifier
                    .background(color = Color(0xFF4682B4), shape = RoundedCornerShape(16.dp))
                    .padding(30.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Outlined.Cloud,
                        contentDescription = "Cloud",
                        modifier = Modifier.size(50.dp)
                    )
                    Text(
                        text = "${temperature ?: "Loading..."}°C",
                        fontSize = 55.sp,
                        modifier = Modifier.padding(start = 16.dp),
                        color = Color.White
                    )
                }
            }
        }
    }
}