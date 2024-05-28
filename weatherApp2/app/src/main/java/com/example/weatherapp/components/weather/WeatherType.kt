package com.example.weatherapp.components.weather

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cloud
import androidx.compose.material.icons.outlined.Grain
import androidx.compose.material.icons.outlined.Thunderstorm
import androidx.compose.material.icons.outlined.Water
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.material.icons.outlined.WbCloudy
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.ui.graphics.vector.ImageVector


sealed class WeatherType(
    val weatherDesc: String,
    val icon: ImageVector
) {
    object ClearSky : WeatherType(
        weatherDesc = "Clear sky",
        icon = Icons.Outlined.WbSunny
    )
    object MainlyClear : WeatherType(
        weatherDesc = "Mainly clear",
        icon = Icons.Outlined.WbSunny
    )
    object PartlyCloudy : WeatherType(
        weatherDesc = "Partly cloudy",
        icon = Icons.Outlined.WbCloudy
    )
    object Overcast : WeatherType(
        weatherDesc = "Overcast",
        icon = Icons.Outlined.WbCloudy
    )
    object Foggy : WeatherType(
        weatherDesc = "Foggy",
        icon = Icons.Outlined.Water
    )
    object DepositingRimeFog : WeatherType(
        weatherDesc = "Depositing rime fog",
        icon = Icons.Outlined.Water
    )
    object LightDrizzle : WeatherType(
        weatherDesc = "Light drizzle",
        icon = Icons.Outlined.WaterDrop
    )
    object ModerateDrizzle : WeatherType(
        weatherDesc = "Moderate drizzle",
        icon = Icons.Outlined.WaterDrop
    )
    object DenseDrizzle : WeatherType(
        weatherDesc = "Dense drizzle",
        icon = Icons.Outlined.WaterDrop
    )
    object LightFreezingDrizzle : WeatherType(
        weatherDesc = "Slight freezing drizzle",
        icon = Icons.Outlined.WaterDrop
    )
    object DenseFreezingDrizzle : WeatherType(
        weatherDesc = "Dense freezing drizzle",
        icon = Icons.Outlined.WaterDrop
    )
    object SlightRain : WeatherType(
        weatherDesc = "Slight rain",
        icon = Icons.Outlined.WaterDrop
    )
    object ModerateRain : WeatherType(
        weatherDesc = "Rainy",
        icon = Icons.Outlined.WaterDrop
    )
    object HeavyRain : WeatherType(
        weatherDesc = "Heavy rain",
        icon = Icons.Outlined.WaterDrop
    )
    object HeavyFreezingRain: WeatherType(
        weatherDesc = "Heavy freezing rain",
        icon = Icons.Outlined.WaterDrop
    )
    object SlightSnowFall: WeatherType(
        weatherDesc = "Slight snow fall",
        icon = Icons.Outlined.Grain
    )
    object ModerateSnowFall: WeatherType(
        weatherDesc = "Moderate snow fall",
        icon = Icons.Outlined.Grain
    )
    object HeavySnowFall: WeatherType(
        weatherDesc = "Heavy snow fall",
        icon = Icons.Outlined.Grain
    )
    object SnowGrains: WeatherType(
        weatherDesc = "Snow grains",
        icon = Icons.Outlined.Grain
    )
    object SlightRainShowers: WeatherType(
        weatherDesc = "Slight rain showers",
        icon = Icons.Outlined.WaterDrop
    )
    object ModerateRainShowers: WeatherType(
        weatherDesc = "Moderate rain showers",
        icon = Icons.Outlined.WaterDrop
    )
    object ViolentRainShowers: WeatherType(
        weatherDesc = "Violent rain showers",
        icon = Icons.Outlined.WaterDrop
    )
    object SlightSnowShowers: WeatherType(
        weatherDesc = "Light snow showers",
        icon = Icons.Outlined.Grain
    )
    object HeavySnowShowers: WeatherType(
        weatherDesc = "Heavy snow showers",
        icon = Icons.Outlined.Grain
    )
    object ModerateThunderstorm: WeatherType(
        weatherDesc = "Moderate thunderstorm",
        icon = Icons.Outlined.Thunderstorm
    )
    object SlightHailThunderstorm: WeatherType(
        weatherDesc = "Thunderstorm with slight hail",
        icon = Icons.Outlined.Thunderstorm
    )
    object HeavyHailThunderstorm: WeatherType(
        weatherDesc = "Thunderstorm with heavy hail",
        icon = Icons.Outlined.Thunderstorm
    )

    companion object {
        fun getWeatherType(code: Int?): WeatherType {
            return when(code) {
                0 -> ClearSky
                1 -> MainlyClear
                2 -> PartlyCloudy
                3 -> Overcast
                45 -> Foggy
                48 -> DepositingRimeFog
                51 -> LightDrizzle
                53 -> ModerateDrizzle
                55 -> DenseDrizzle
                56 -> LightFreezingDrizzle
                57 -> DenseFreezingDrizzle
                61 -> SlightRain
                63 -> ModerateRain
                65 -> HeavyRain
                66 -> LightFreezingDrizzle
                67 -> HeavyFreezingRain
                71 -> SlightSnowFall
                73 -> ModerateSnowFall
                75 -> HeavySnowFall
                77 -> SnowGrains
                80 -> SlightRainShowers
                81 -> ModerateRainShowers
                82 -> ViolentRainShowers
                85 -> SlightSnowShowers
                86 -> HeavySnowShowers
                95 -> ModerateThunderstorm
                96 -> SlightHailThunderstorm
                99 -> HeavyHailThunderstorm
                else -> ClearSky
            }
        }
    }
}