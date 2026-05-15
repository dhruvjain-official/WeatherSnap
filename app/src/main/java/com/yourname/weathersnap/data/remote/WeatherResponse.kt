package com.yourname.weathersnap.data.remote

data class WeatherResponse(

    val current: CurrentWeather
)

data class CurrentWeather(

    val temperature_2m: Double,

    val relative_humidity_2m: Int,

    val wind_speed_10m: Double,

    val surface_pressure: Double,

    val weather_code: Int
)