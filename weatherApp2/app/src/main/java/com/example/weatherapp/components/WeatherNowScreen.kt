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
import com.example.weatherapp.components.weather.Weather
import com.example.weatherapp.components.weather.WeatherType
import com.example.weatherapp.components.weather.WeatherViewModel
import com.example.weatherapp.components.weather.WeatherViewModelFactory

@Composable
fun WeatherNowScreen() {
    val viewModel: WeatherViewModel = viewModel(factory = WeatherViewModelFactory.create())
    val weather = viewModel.weather.collectAsState().value

    viewModel.getWeather()

    //scroll state for inner content
    val scrollState = rememberScrollState()

    val currentTime = weather?.current?.time
    val timeList = weather?.hourly?.time
    println("Current Time: $currentTime")
    println("Time List: $timeList")

    //antaa null välillä
    val timeIndexNow = getCurrentTimesIndex(weather = weather, weatherList = timeList, currentTime = currentTime)
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
                    //imageVector = Icons.Outlined.Cloud,
                    contentDescription = WeatherType.getWeatherType(weather?.current?.weather_code).weatherDesc,
                    modifier = Modifier.size(60.dp)
                )
                Spacer(modifier = Modifier.size(25.dp))
                Text(
                    text = "${weather?.current?.temperature_2m ?: "Loading"}°C",
                    fontSize = 55.sp,
                    modifier = Modifier.padding(start = 16.dp),
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.size(20.dp))
            Box(
                modifier = Modifier
                    .padding(30.dp)
                    .fillMaxWidth()
                    .heightIn(min = 0.dp, max = 400.dp)
            ) {
                Column(
                    modifier = Modifier.verticalScroll(scrollState)
                ) {

                    // Today's forecast
                    Column {
                        val startHour = timeIndexNow ?: 0
                        if (timeList != null) {
                            for (hour in startHour until startHour + 24) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Spacer(modifier = Modifier.size(35.dp))
                                    Text(
                                        text = getHourFromTime(index = hour, weather = weather)
                                            ?: "Loading...",
                                        fontSize = 30.sp,
                                        modifier = Modifier.padding(start = 16.dp),
                                        color = Color.White
                                    )
                                    Spacer(modifier = Modifier.size(50.dp))
                                    Icon(
                                        imageVector = WeatherType.getWeatherType(weather.hourly.weather_code[hour]).icon,
                                        //imageVector = Icons.Outlined.Cloud,
                                        contentDescription = WeatherType.getWeatherType(weather.hourly.weather_code[hour]).weatherDesc,
                                        modifier = Modifier.size(30.dp)
                                    )
                                    Spacer(modifier = Modifier.size(15.dp))
                                    Text(
                                        text = "${weather.hourly.temperature_2m[hour] ?: "Loading"}°C",
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
}

fun getHourFromTime(index: Int, weather: Weather?): String? {
    val timeString = weather?.hourly?.time?.get(index)
    return timeString?.substring(11, 13)
}

fun getCurrentTimesIndex(weather: Weather?, weatherList: List<String>?, currentTime: String?) : Int? {
    if (weatherList != null && currentTime != null) {
        for (i in weatherList.indices) {
            if (weatherList[i] == currentTime) {
                return i
            }
        }
    }
    return null
}
