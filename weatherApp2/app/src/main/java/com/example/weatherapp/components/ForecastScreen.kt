package com.example.weatherapp.components


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cloud
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ForecastScreen() {
    var searchState : String by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
        Column {
            Text("search")
            OutlinedTextField(
                value = searchState,
                onValueChange = { searchState = it }
            )
            Text(searchState)
            Column {
                Row {
                    Icon(imageVector = Icons.Outlined.Cloud, contentDescription = "Cloud")
                    Text("Monday", modifier = Modifier.padding(start = 30.dp))
                }
                Row {
                    Icon(imageVector = Icons.Outlined.WbSunny, contentDescription = "Sun")
                    Text("Tuesday", modifier = Modifier.padding(start = 30.dp))
                }
                Row {
                    Icon(imageVector = Icons.Outlined.WaterDrop, contentDescription = "Rain")
                    Text("Wednesday", modifier = Modifier.padding(start = 30.dp))
                }
            }
        }

    }

}