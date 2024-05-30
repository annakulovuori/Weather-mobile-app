package com.example.weatherapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.Icon
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
import com.example.weatherapp.components.location.locationInfo
import com.example.weatherapp.components.weather.Weather
import com.example.weatherapp.components.weather.WeatherType
import com.example.weatherapp.components.weather.WeatherViewModel
import com.example.weatherapp.components.weather.WeatherViewModelFactory

@Composable
fun WeatherNowScreen() {
    val viewModel: WeatherViewModel = viewModel(factory = WeatherViewModelFactory.create())
    val weather = viewModel.weather.collectAsState().value

    locationInfo(weatherViewModel = viewModel)

    // state for showing the loading indicator
    var loading by remember { mutableStateOf(false) }

    //scroll state for inner content
    val scrollState = rememberScrollState()

    val currentTime = currentTimeToEvenHour(weather = weather)
    val timeList = weather?.hourly?.time
    println("Current Time: $currentTime")
    println("Time List: $timeList")

    val timeIndexNow = getCurrentTimesIndex(weatherList = timeList, currentTime = currentTime)
    println("Time Index Now: $timeIndexNow")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 5.dp),
        contentAlignment = Alignment.Center,

        ) {
        Column {
            Text(
                text = "TODAY",
                fontSize = 18.sp,
                modifier = Modifier.padding(start = 70.dp),
                color = Color.Yellow
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.size(70.dp))
                Icon(
                    imageVector = WeatherType.getWeatherType(weather?.current?.weather_code).icon,
                    contentDescription = WeatherType.getWeatherType(weather?.current?.weather_code).weatherDesc,
                    modifier = Modifier.size(60.dp)
                )
                Spacer(modifier = Modifier.size(25.dp))
                if (weather?.current?.temperature_2m != null) {
                    Text(
                        text = "${weather.current.temperature_2m}°C",
                        fontSize = 55.sp,
                        modifier = Modifier.padding(start = 16.dp),
                        color = Color.White
                    )
                } else {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .width(74.dp)
                            .padding(start = 16.dp),
                        color = Color.White
                    )
                }
            }
            Spacer(modifier = Modifier.size(25.dp))
            Row {
                Text(text = "HOUR",
                    fontSize = 18.sp,
                    color = Color.Yellow,
                    modifier = Modifier.padding(start = 75.dp)
                )

                Text(text = "°C",
                    fontSize = 18.sp,
                    color = Color.White,
                    modifier = Modifier.padding(start = 140.dp)
                )
            }
            Box(
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()
                    .heightIn(min = 0.dp, max = 400.dp)
            ) {

                // Today's forecast
                Column(
                    modifier = Modifier.verticalScroll(scrollState)
                ) {
                    val startHour = timeIndexNow ?: 0
                    if (timeList != null) {
                        for (hour in startHour until startHour + 24) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Spacer(modifier = Modifier.size(50.dp))

                                val hourText = getHourFromTime(index = hour, weather = weather)
                                if (hourText != null) {
                                    Text(
                                        text = hourText,
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
                                Icon(
                                    imageVector = WeatherType.getWeatherType(weather.hourly.weather_code[hour]).icon,
                                    contentDescription = WeatherType.getWeatherType(weather.hourly.weather_code[hour]).weatherDesc,
                                    modifier = Modifier.size(30.dp)
                                )
                                Spacer(modifier = Modifier.size(15.dp))
                                Text(
                                    text = "${weather.hourly.temperature_2m[hour]}°C",
                                    fontSize = 30.sp,
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

fun getHourFromTime(index: Int, weather: Weather?): String? {
    val timeString = weather?.hourly?.time?.get(index)
    return timeString?.substring(11, 13)
}

fun currentTimeToEvenHour(weather: Weather?): String {
    val timeString = weather?.current?.time
    return "${timeString?.substring(0, 14)}00"
}


fun getCurrentTimesIndex(weatherList: List<String>?, currentTime: String?) : Int? {
    if (weatherList != null && currentTime != null) {
        for (i in weatherList.indices) {
            if (weatherList[i] == currentTime) {
                return i
            }
        }
    }
    return null
}
