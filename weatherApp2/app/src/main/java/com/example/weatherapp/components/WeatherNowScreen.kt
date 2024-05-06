package com.example.weatherapp.components


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun WeatherNowScreen(navController: NavController) {

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
        Button(onClick = {
            navController.navigate("ForecastScreen")
        }) {
            Text(text = "Go to Screen 2")
        }
    }
}