package com.example.weatherapp.components


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDownward
import androidx.compose.material.icons.outlined.ArrowForwardIos
import androidx.compose.material.icons.outlined.Cloud
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.weatherapp.components.location.locationInfo
import com.example.weatherapp.components.weather.Weather
import com.example.weatherapp.components.weather.WeatherType
import com.example.weatherapp.components.weather.WeatherViewModel
import com.example.weatherapp.components.weather.WeatherViewModelFactory

@Composable
fun ForecastScreen() {
    val viewModel: WeatherViewModel = viewModel(factory = WeatherViewModelFactory.create())
    val weather = viewModel.weather.collectAsState().value

    locationInfo(weatherViewModel = viewModel)

    //scroll state for inner content
    val scrollState = rememberScrollState()

    var expandedDayIndex by remember { mutableStateOf(-1) }

    // List of daily times
    val timeList = weather?.daily?.time

    Box(
        modifier = Modifier
            .padding(top = 60.dp),
        contentAlignment = Alignment.Center,

        ) {
        Column {
            // Title
            Text(text = "7 DAYS",
                fontSize = 35.sp,
                color = Color.Yellow,
                modifier = Modifier.padding(start = 130.dp)
            )
            Spacer(modifier = Modifier.size(45.dp))
            Box(
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()
                    .heightIn(min = 0.dp, max = 500.dp)
            ) {

                Column(
                    modifier = Modifier.verticalScroll(scrollState)
                ) {
                    // Info titles
                    Row {
                        Text(text = "DAY",
                            fontSize = 20.sp,
                            color = Color.Yellow,
                            modifier = Modifier.padding(start = 50.dp)
                        )

                        Text(text = "MAX °C",
                            fontSize = 20.sp,
                            color = Color.White,
                            modifier = Modifier.padding(start = 150.dp)
                        )
                    }

                    Column {
                        if (timeList != null) {
                            // Loop from 0 to 7 because a week is 7 days long
                            for (day in 0 until 7) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Spacer(modifier = Modifier.size(20.dp))

                                    val dayText = getDayFromDate(index = day, weather = weather)
                                    val monthText = getMonthFromDate(day, weather)

                                    if (dayText != null && monthText != null) {
                                        // Date dd.mm.
                                        Text(
                                            text = "$dayText.$monthText",
                                            fontSize = 30.sp,
                                            modifier = Modifier.padding(start = 16.dp),
                                            color = Color.White
                                        )
                                    } else {
                                        CircularProgressIndicator(
                                            modifier = Modifier
                                                .padding(start = 16.dp),
                                            color = Color.White
                                        )
                                    }
                                    Spacer(modifier = Modifier.size(50.dp))
                                    // Icon
                                    Icon(
                                        imageVector = WeatherType.getWeatherType(weather.daily.weather_code[day]).icon,
                                        contentDescription = WeatherType.getWeatherType(weather.daily.weather_code[day]).weatherDesc,
                                        modifier = Modifier.size(30.dp)
                                    )
                                    Spacer(modifier = Modifier.size(15.dp))
                                    // Temperature
                                    Text(
                                        text = "${weather.daily.temperature_2m_max[day]}°C",
                                        fontSize = 30.sp,
                                        modifier = Modifier.padding(start = 16.dp),
                                        color = Color.White
                                    )
                                }
                                    // Button to open and close more detailed information
                                    Row {
                                        Button(
                                            onClick = {
                                                expandedDayIndex = if (expandedDayIndex == day) {
                                                    -1
                                                } else {
                                                    day
                                                }
                                            },
                                            modifier = Modifier
                                                .padding(start = 10.dp),
                                            shape = RoundedCornerShape(12.dp),
                                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF87CEEB))
                                        ) {
                                            Icon(
                                                imageVector = Icons.Outlined.ArrowForwardIos,
                                                contentDescription = "Arrow",
                                                modifier = Modifier.size(18.dp),
                                                tint = Color.White,
                                            )
                                        }
                                        if (expandedDayIndex == day) {
                                            // Min temperature
                                            Text(text = "Min: ${weather.daily.temperature_2m_min[day]}°C",
                                                fontSize = 20.sp,
                                                modifier = Modifier.padding(start = 5.dp, top = 5.dp),
                                                color = Color.White
                                            )
                                            // Weather Description
                                            Text(text = WeatherType.getWeatherType(weather.daily.weather_code[day]).weatherDesc,
                                                fontSize = 18.sp,
                                                modifier = Modifier.padding(start = 10.dp, top = 5.dp),
                                                color = Color.Yellow
                                            )
                                        }
                                    }
                            }
                        }
                    }
                }
            }
        }

    }
}
// Returns day from date string
fun getDayFromDate(index: Int, weather: Weather?): String? {
    val timeString = weather?.daily?.time?.get(index)
    return timeString?.substring(8, 10)
}
// returns month from date string
fun getMonthFromDate(index: Int, weather: Weather?): String? {
    val timeString = weather?.daily?.time?.get(index)
    return timeString?.substring(5, 7)
}