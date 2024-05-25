package com.example.weatherapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.example.weatherapp.components.weather.WeatherViewModel
import com.example.weatherapp.components.weather.WeatherViewModelFactory

@Composable
fun WeatherNowScreen() {
    val viewModel: WeatherViewModel = viewModel(factory = WeatherViewModelFactory.create())
    val temperature = viewModel.temperature.collectAsState().value

    viewModel.getTemperature()

    //scroll state for inner content
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 5.dp),
        contentAlignment = Alignment.Center,

    ) {
        //delete
        Column {
            Text(
                text = "TODAY",
                fontSize = 18.sp,
                modifier = Modifier.padding(start = 70.dp),
                color = Color.White
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.size(70.dp))
                Icon(
                    imageVector = Icons.Outlined.Cloud,
                    contentDescription = "Cloud",
                    modifier = Modifier.size(60.dp)
                )
                Spacer(modifier = Modifier.size(25.dp))
                Text(
                    text = "${temperature ?: "Loading..."}°C",
                    fontSize = 55.sp,
                    modifier = Modifier.padding(start = 16.dp),
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.size(20.dp))
            Box (
                modifier = Modifier
                    //.background(color = Color(0xFF76B7DE), shape = RoundedCornerShape(16.dp))
                    .padding(30.dp)
                    .fillMaxWidth()
                    .heightIn(min = 0.dp, max = 400.dp)
            ){
                Column(
                    modifier = Modifier.verticalScroll(scrollState)
                ) {

                    // Today's forecast
                    Column {
                        for (hour in 0 until 24) {
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
                        }
                    }
                }
            }
        }

        }
    }