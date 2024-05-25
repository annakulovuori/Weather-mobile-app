package com.example.weatherapp.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
                Column {
                    Text(
                        text = "TODAY",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(start = 20.dp),
                        color = Color.White

                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Spacer(modifier = Modifier.size(30.dp))
                        Icon(
                            imageVector = Icons.Outlined.Cloud,
                            contentDescription = "Cloud",
                            modifier = Modifier.size(50.dp)
                        )
                        Spacer(modifier = Modifier.size(25.dp))
                        Text(
                            text = "${temperature ?: "Loading..."}°C",
                            fontSize = 55.sp,
                            modifier = Modifier.padding(start = 16.dp),
                            color = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.size(50.dp))

                    //Todays forecast

                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "01.00",
                                fontSize = 30.sp,
                                modifier = Modifier.padding(start = 16.dp),
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.size(50.dp))
                            Icon(
                                imageVector = Icons.Outlined.WbSunny,
                                contentDescription = "Sun",
                                modifier = Modifier.size(30.dp)
                            )
                            Spacer(modifier = Modifier.size(15.dp))
                            Text(
                                text = "${temperature ?: "Loading..."}°C",
                                fontSize = 35.sp,
                                modifier = Modifier.padding(start = 16.dp),
                                color = Color.White
                            )
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "02.00",
                                fontSize = 30.sp,
                                modifier = Modifier.padding(start = 16.dp),
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.size(50.dp))
                            Icon(
                                imageVector = Icons.Outlined.WaterDrop,
                                contentDescription = "Rain",
                                modifier = Modifier.size(30.dp)
                            )
                            Spacer(modifier = Modifier.size(15.dp))
                            Text(
                                text = "${temperature ?: "Loading..."}°C",
                                fontSize = 35.sp,
                                modifier = Modifier.padding(start = 16.dp),
                                color = Color.White
                            )
                        }
                    }
                }

            }
        }
    }
}