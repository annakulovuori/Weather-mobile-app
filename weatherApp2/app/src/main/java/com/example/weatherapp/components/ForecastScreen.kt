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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cloud
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.material.icons.outlined.WbSunny
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
import com.example.weatherapp.components.weather.Weather
import com.example.weatherapp.components.weather.WeatherViewModel
import com.example.weatherapp.components.weather.WeatherViewModelFactory

@Composable
fun ForecastScreen() {
    val viewModel: WeatherViewModel = viewModel(factory = WeatherViewModelFactory.create())
    val weather = viewModel.weather.collectAsState().value

    viewModel.getWeather()

    //scroll state for inner content
    val scrollState = rememberScrollState()

    val timeList = weather?.daily?.time

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 5.dp),
        contentAlignment = Alignment.Center,

        ) {
        Column {
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
                        if (timeList != null) {
                            for (hour in 0 until 7) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Spacer(modifier = Modifier.size(35.dp))
                                    Text(
                                        text = getDayFromDate(index = hour, weather = weather)
                                            ?: "Loading",
                                        fontSize = 30.sp,
                                        modifier = Modifier.padding(start = 16.dp),
                                        color = Color.White
                                    )
                                    Spacer(modifier = Modifier.size(50.dp))
                                    Icon(
                                        imageVector = Icons.Outlined.WaterDrop,
                                        contentDescription = "Sun",
                                        modifier = Modifier.size(30.dp)
                                    )
                                    Spacer(modifier = Modifier.size(15.dp))
                                    Text(
                                        text = "${weather.daily.temperature_2m_max[hour] ?: "Loading"}°C",
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
fun getDayFromDate(index: Int, weather: Weather?): String? {
    val timeString = weather?.daily?.time?.get(index)
    return timeString?.substring(8, 10)
}